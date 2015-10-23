package com.calcalc.samps_000.workoutapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by samps_000 on 10/18/2015.
 */
public class Profile extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Log.d("nav", "inprofile");

        NavAdapter.addDrawerItem(this, 3);
        NavAdapter.drawerListener(this, 3);

        SharedPreferences share = Profile.this.getSharedPreferences("logData", Context.MODE_PRIVATE);

        EditText goal = (EditText) findViewById(R.id.calorieGoalEdit);
        String calGoal = share.getString("track", null);
        if(calGoal != null){
            goal.setHint(calGoal);
        }
        share.edit().commit();

    }
    public void settingsClicked(View view){
        Intent settings = new Intent(Profile.this, Settings.class);
        startActivity(settings);
    }

    public void changeGoalButtonClick(View view){
        EditText goal = (EditText) findViewById(R.id.calorieGoalEdit);
        String calGoal = goal.getText().toString();
        Log.d("set", calGoal);
        SharedPreferences share = Profile.this.getSharedPreferences("logData", Context.MODE_PRIVATE);
        String curGoal = share.getString("track", null);
        Log.d("set", curGoal);
        SharedPreferences.Editor edit = share.edit();
        edit.remove("track");
        edit.commit();
        edit.putString("track", calGoal);
        edit.commit();
        curGoal = share.getString("track", null);
        Log.d("set", curGoal);
        Toast.makeText(this, "Goal changed to " + calGoal, Toast.LENGTH_LONG).show();

        if(view != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        goal.clearFocus();

    }
}
