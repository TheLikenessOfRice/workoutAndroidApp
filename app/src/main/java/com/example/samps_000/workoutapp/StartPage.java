package com.example.samps_000.workoutapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class StartPage extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("testing", "start create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);
    }

    public void settingsClicked(View view){
        Intent settings = new Intent(StartPage.this, Settings.class);
        startActivity(settings);
    }

    public void foodSearchClick(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void calculateClicked(View view){
        Intent i = new Intent(StartPage.this, CalcActivity.class);
        startActivity(i);
    }
}
