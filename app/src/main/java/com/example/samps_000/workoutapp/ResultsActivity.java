package com.example.samps_000.workoutapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Window;


public class ResultsActivity extends ActionBarActivity {


    private TextView lose1, lose2, main1, main2, gain1,gain2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);



        // intialize TextViews
        lose1 = (TextView) findViewById(R.id.lose_input1);
        lose2 = (TextView) findViewById(R.id.lose_input2);
        main1 = (TextView) findViewById(R.id.main_input1);
        main2 = (TextView) findViewById(R.id.main_input2);
        gain1 = (TextView) findViewById(R.id.gain_input1);
        gain2 = (TextView) findViewById(R.id.gain_input2);


       Bundle b = getIntent().getExtras();
        double beto_result =  b.getDouble("key"); // get calculation #
        String beto_string = b.getString("key2"); //determine if male or female calculation

        String beto_male = "male";
        String beto_female = "female";

        //change result to int;
        int beto_result2 = (int) beto_result;

        //round to nearest 10 for simpler number
        beto_result2 = ((beto_result2 + 9)/10) * 10;

        // calculation of maintain and gain

        int calc_lose1;
        int calc_lose2 ;
        int calc_maintain1;
        int calc_maintain2;
        int calc_gain1;
        int calc_gain2;



        if(beto_string.equals(beto_male))
        {
             calc_lose1 = beto_result2 - 100;
             calc_lose2 = beto_result2 + 100;
             calc_maintain1 = beto_result2 + 450;
             calc_maintain2 = beto_result2 + 650;
             calc_gain1 = beto_result2 + 900;
             calc_gain2 = beto_result2 + 1100;

            /*Toast t;
            t = Toast.makeText(this, "Male",Toast.LENGTH_LONG);  //Test Toast
            t.show(); */

            lose1.setText(Integer.toString(calc_lose1)+" Cal.");
            lose2.setText(Integer.toString(calc_lose2)+" Cal.");


            main1.setText(Integer.toString(calc_maintain1)+" Cal.");
            main2.setText(Integer.toString(calc_maintain2)+" Cal.");

            gain1.setText(Integer.toString(calc_gain1)+" Cal.");
            gain2.setText(Integer.toString(calc_gain2) + " Cal.");
        }

        if(beto_string.equals(beto_female))
        {
             calc_lose1 = beto_result2 - 150;
             calc_lose2 = beto_result2 + 50;
             calc_maintain1 = beto_result2 + 300;
             calc_maintain2 = beto_result2 + 500;
             calc_gain1 = beto_result2 + 800;
             calc_gain2 = beto_result2 + 1000;

           /* Toast t;
            t = Toast.makeText(this, "Female",Toast.LENGTH_LONG);  // test toast
            t.show(); */

            lose1.setText(Integer.toString(calc_lose1)+" Cal.");
            lose2.setText(Integer.toString(calc_lose2)+" Cal.");


            main1.setText(Integer.toString(calc_maintain1)+" Cal.");
            main2.setText(Integer.toString(calc_maintain2)+" Cal.");

            gain1.setText(Integer.toString(calc_gain1)+" Cal.");
            gain2.setText(Integer.toString(calc_gain2) + " Cal.");
        }


        
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
