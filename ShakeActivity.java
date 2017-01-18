package ca.georgebrown.comp3074.assignment2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by joe_z on 2016-10-31.
 */

public class ShakeActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shake);
     //set the textview
        TextView shake = (TextView) findViewById(R.id.shakeDetected);

        //add button and set the listen to the exit the activity
        Button btnexit = (Button) findViewById(R.id.button);
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shake.setText("A shake was Detected");
    }
}
