package com.example.samps_000.workoutapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Settings extends Activity{

    Spinner themes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeChange.onActivityCreateSetTheme(this);
        setContentView(R.layout.settings);
    }
/*
    public void darkButtonClicked(View view){
        themeChange.changeToTheme(this, themeChange.THEME_DARK);
        StartPage newStart = new StartPage();
        newStart.changeTheme("dark");
    }
    public void lightButtonClicked(View view) {
        themeChange.changeToTheme(this, themeChange.THEME_LIGHT);
        StartPage newStart = new StartPage();
        newStart.changeTheme("light");
    }
    */
    public void saveClicked(View view){
        finish();
    }
}
