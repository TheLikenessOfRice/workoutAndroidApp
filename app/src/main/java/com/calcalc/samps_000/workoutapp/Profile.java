package com.calcalc.samps_000.workoutapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by samps_000 on 10/18/2015.
 */
public class Profile extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences share = Profile.this.getSharedPreferences("logData", MODE_PRIVATE);

        EditText goal = (EditText) findViewById(R.id.calorieGoalEdit);
        String calGoal = share.getString("track", null);
        if(calGoal != null){
            goal.setHint(calGoal);
        }

    }
    public void settingsClicked(View view){
        Intent settings = new Intent(Profile.this, Settings.class);
        startActivity(settings);
    }
}
