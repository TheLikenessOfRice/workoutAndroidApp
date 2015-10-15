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
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        ImageView menu_image = (ImageView) findViewById(R.id.menu_image);
        menu_image.getLayoutParams().height = (int) (dpHeight/3);

        SharedPreferences prefs = getSharedPreferences("logData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        int i = 1;
        while(null != prefs.getString(String.valueOf(i) + " title", null)) {
            editor.remove(String.valueOf(i) + " title");
            editor.remove(String.valueOf(i) + " cal");
            editor.remove(String.valueOf(i) + " no");
            i+=1;
        }
        editor.apply();

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

    public void logRowClick(View view){
        Intent i = new Intent(StartPage.this, FoodLog.class);
        startActivity(i);
    }
}
