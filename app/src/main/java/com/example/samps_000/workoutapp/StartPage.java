package com.example.samps_000.workoutapp;


import android.app.Activity;
import android.content.Intent;
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
/*        final View row = (View) findViewById(R.id.bottomRow);
        final TableRow rowName = (TableRow) findViewById(R.id.bottomRow);
        row.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_DOWN) {
                            rowName.setBackgroundResource(R.drawable.table_clicked);
                        }
                        if(event.getAction() == MotionEvent.ACTION_UP){
                            rowName.setBackgroundResource(R.drawable.table);
                        }
                        Intent i = new Intent(StartPage.this, MainActivity.class);
                        startActivity(i);
                        return true;
                    }
                });

    */
    }
    public void foodSearchClick(View view){
        Intent i = new Intent(StartPage.this, MainActivity.class);
        startActivity(i);
    }
}
