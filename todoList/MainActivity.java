package ca.georgebrown.comp3074.todolist;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TodoListDatabaseHelper helper;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add a toolbar for the menu
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        //Add listbox code here
        listView = (ListView) findViewById(R.id.listview);

        helper = new TodoListDatabaseHelper(this);
        refresh(listView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    // inflate the menu
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_task:

                final EditText task = new EditText(this);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Add ToDO item");
                builder.setMessage("What would you like to add?");
                builder.setView(task);

                // Add the buttons
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        String item = String.valueOf(task.getText());
                        //Create and open the connection to SQL LITE DB
                        SQLiteDatabase db = helper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("task", item);
                        db.insert("tasks",
                                null,
                                values);
                        db.close();
                        refresh(listView);
                    }
                });

                builder.setNegativeButton("Cancel", null);
                builder.create();
                builder.show();
                return true;

            default:
            return super.onOptionsItemSelected(item);
        }
    }

    public void refresh(ListView view){
        try{
            Log.d("Hello", "Hello");
            //Create a readable database so we can get the values and place them into the listview
            SQLiteDatabase db = helper.getReadableDatabase();

            //Create a cursor
            Cursor cursor = db.query("tasks", new String[]{"_id", "task"},
                    null, null, null, null, null);

            //Move the cursor to the first record
            cursor.moveToFirst();

            //Log.v("cursor",cursor.getString(1).toString());
            //Adapter for the Listview

            TaskCursorAdapter taskAdapter = new TaskCursorAdapter(this, cursor, 0);

             //Set the listview to the adapter
            view.setAdapter(taskAdapter);

        } catch(SQLiteException e) {
            //Show message if you can't connect to database
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void deleteTask(View view) {

        //Delete the task and remove it from the list view
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task);
        String task = String.valueOf(taskTextView.getText());
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("tasks",
                "task" + " = ?",
                new String[]{task});
        db.close();
        refresh(listView);
    }
}