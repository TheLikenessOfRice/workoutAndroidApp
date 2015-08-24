package com.example.samps_000.workoutapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Settings extends Activity {

    Spinner themes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeChange.onActivityCreateSetTheme(this);
        setContentView(R.layout.settings);

        themes = (Spinner) findViewById(R.id.themes);
        String[] items = new String[]{"Dark", "Light"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        themes.setAdapter(adapter);
    }

    public void saveClicked(View view){
        String item = themes.getSelectedItem().toString();
        if(item == "Dark")
            themeChange.changeToTheme(this, themeChange.DARK);
        else
            themeChange.changeToTheme(this, themeChange.LIGHT);
    }
}
