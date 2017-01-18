package ca.georgebrown.comp3074.assignment2;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.R.attr.x;
import static android.R.attr.y;

public class MainActivityFragment extends Fragment {

    //set all variables
    SensorManager sensorManager;
    private float current;
    private float last;
    private float acceleration;
    TextView accel;
    float lastAccel = 0;

    private final SensorEventListener sensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {

           //set the x,y,z values to  sensor values
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];


            boolean init = false;
            //set the textbox's to the values of the sensor

            accel = (TextView) getActivity().findViewById(R.id.accel);

            //Find the difference between last time it was changed
            current = (float) Math.sqrt((double) (x * x + y * y + z * z));

            //find the difference between the current and last
            float delta = current - last;
            acceleration = acceleration * 0.9f + delta;

            //Last equals current after math is done
            last = current;

            //only show major changes
            if((acceleration - lastAccel) > .1)
            accel.setText(Float.toString(acceleration));

            //if the difference is greater than 10 we start a new intent that a shake was detected
            if (acceleration > 10){
                Intent intent = new Intent(getActivity(), ShakeActivity.class);
                startActivity(intent);
            }

            //keep track of the last acceleration number
            lastAccel = acceleration;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            //Not being used
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Create the sensormanager and register the eventlistener
        sensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
              SensorManager.SENSOR_DELAY_NORMAL);

        //Set default values for variables
        acceleration = 0f;
        current = SensorManager.GRAVITY_EARTH;
        last = SensorManager.GRAVITY_EARTH;

        //Return to main
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onResume() {
        //On resume we want to reactivate the Listener
        super.onResume();
        sensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(sensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        //sensorManager = (SensorManager) this.getActivity().getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public void onPause() {
        //Stop the listener here.
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }
}