package ca.georgebrown.comp3074.todolist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by joe_z on 2016-11-13.
 */

public class TaskCursorAdapter extends CursorAdapter {


    private LayoutInflater cursorInflator;

    public TaskCursorAdapter(Context context, Cursor c, int flags) {
        //Constructor that will inflate the layout with the textview and the button
        super(context, c, flags);
        cursorInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//returns the layout
        return cursorInflator.inflate(R.layout.item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //sets the values of the textbox
        TextView textViewTitle = (TextView) view.findViewById(R.id.task);
        String title = cursor.getString( cursor.getColumnIndex("task"));
        textViewTitle.setText(title);
    }
}
