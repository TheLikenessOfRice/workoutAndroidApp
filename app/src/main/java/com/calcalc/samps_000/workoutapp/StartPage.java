package com.calcalc.samps_000.workoutapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class StartPage extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("testing", "start create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);


        NavAdapter.addDrawerItem(this, 0);
        NavAdapter.drawerListener(this, 0);

    }

    public void profileRowClick(View view){
       Intent i = new Intent(this, Profile.class);
        startActivity(i);
    }

    public void foodSearchClick(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void calculateClicked(View view){
        Intent i = new Intent(StartPage.this, CalcActivity.class);
        startActivity(i);
    }

    public void logRowClick(View view){
        Intent i = new Intent(StartPage.this, FoodLog.class);
        startActivity(i);
    }
}
