package ca.georgebrown.comp3074.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by joe_z on 2016-11-12.
 */


public class TodoListDatabaseHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "todoDB";
    private static int dbVersion = 0;

    // default constructor
    public TodoListDatabaseHelper(Context context) {
        super(context, DBNAME, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDataBase(db,dbVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDataBase(db, dbVersion);
    }

    private void updateMyDataBase(SQLiteDatabase db, int oldVersion) {
     if(oldVersion > 1) {
            // create the table
            db.execSQL("CREATE TABLE tasks ("
                    + " _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " task TEXT NOT NULL"
                    + ");");
        insertItem(db, "Hello");
         dbVersion++;
        }
    }
    private static void insertItem(SQLiteDatabase db, String task){
        // Create a Content values so we can insert into database
        ContentValues tasks = new ContentValues();
        tasks.put("task", task);

        // insert into database
        db.insert("tasks", null, tasks);
    }

    public static void deleteItem(SQLiteDatabase db, String task){

    }

}
