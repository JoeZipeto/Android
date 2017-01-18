package ca.georgebrown.comp3034.clicktester;
/******************************************************************
 * Name :Joe Zipeto
 * Date: Sunday september 25
 * Student Number: 100963441
 * */

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View redBack =findViewById(R.id.redBackground);
        View greenBack =findViewById(R.id.greenBackground);
        View blueBack =findViewById(R.id.blueBackground);
        View yellowBack =findViewById(R.id.yellowBackground);


        Button btn = (Button) findViewById(R.id.button);
        //event listener for the red background
        redBack.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(MainActivity.this, "This is a Long Click in the red zone", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        redBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "This is a Short Click in the red zone", Toast.LENGTH_SHORT).show();

            }
        });


        //event listener for the green background
        greenBack.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(MainActivity.this, "This is a Long Click in the green zone", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        greenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "This is a Short Click in the green zone", Toast.LENGTH_SHORT).show();

            }
        });

        //event listener for the yellow background
        yellowBack.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(MainActivity.this, "This is a Long Click in the yellow zone", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        yellowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "This is a Short Click in the yellow zone", Toast.LENGTH_SHORT).show();

            }
        });

        //event listener for the blue background
        blueBack.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(MainActivity.this, "This is a Long Click in the blue zone", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        blueBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "This is a Short Click in blue zone", Toast.LENGTH_SHORT).show();

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Close all process's and exit the app

                finish();
                System.exit(0);
            }
        });

    }
}
