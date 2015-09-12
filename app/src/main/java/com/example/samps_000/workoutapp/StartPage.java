package com.example.samps_000.workoutapp;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

public class StartPage extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);
        TableLayout twoBut = (TableLayout) findViewById(R.id.twoBut);
        final View row = (View) findViewById(R.id.bottomRow);
        final View rowTop = (View) findViewById(R.id.topRow);
        final TableRow topTableRow = (TableRow) findViewById(R.id.topRow);
        final TableRow bottomTableRow = (TableRow) findViewById(R.id.bottomRow);
        row.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_DOWN) {
                            bottomTableRow.setBackgroundResource(R.drawable.table_clicked);
                        }
                        if(event.getAction() == MotionEvent.ACTION_UP){
                            bottomTableRow.setBackgroundResource(R.drawable.table);
                        }
                        Intent i = new Intent(StartPage.this, MainActivity.class);
                        startActivity(i);
                        return true;
                    }
                });
        rowTop.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_DOWN) {
                            topTableRow.setBackgroundResource(R.drawable.table_clicked);
                        }
                        if(event.getAction() == MotionEvent.ACTION_UP){
                            topTableRow.setBackgroundResource(R.drawable.table);
                        }
                        Intent i = new Intent(StartPage.this, CalcActivity.class);
                        startActivity(i);
                        return true;
                    }
                });
    }

    public void settingsClicked(View view){
        Intent settings = new Intent(StartPage.this, Settings.class);
        startActivity(settings);
    }

    public void foodSearchClick(View view){
        Intent i = new Intent(StartPage.this, MainActivity.class);
        startActivity(i);
    }

    public void changeTheme(String theme) {

        final View row = (View) findViewById(R.id.bottomRow);
        final TableRow rowName = (TableRow) findViewById(R.id.bottomRow);
        if(theme == "light")
            rowName.setBackgroundResource(R.drawable.table_button_light);
        else
            rowName.setBackgroundResource(R.drawable.table_button);
    }
}
