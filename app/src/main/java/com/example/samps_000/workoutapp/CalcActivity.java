package com.example.samps_000.workoutapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;




public class CalcActivity extends Activity implements View.OnClickListener {

    private Button calc;
    private EditText age, weight, height1, height2;
    private CheckBox genderM, genderF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        init();
    }

    private void init() {
        calc = (Button) findViewById(R.id.calculate);
        age = (EditText) findViewById(R.id.ageInput);
        weight = (EditText) findViewById(R.id.weightInput);
        height1 = (EditText) findViewById(R.id.heightft);
        height2 = (EditText) findViewById(R.id.heightin);
        genderM = (CheckBox) findViewById(R.id.MaleBox);
        genderF = (CheckBox) findViewById(R.id.FemaleBox);

        calc.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        //Intent i = new Intent(this,ResultsActivity.class);  // intent
        String myString = "beto";
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        String str1 = age.getText().toString(); //change editText to a string
        String str2 = weight.getText().toString();
        String str3 = height1.getText().toString();
        String str4 = height2.getText().toString();
        int ageNum = Integer.parseInt(str1); // change string to an int
        int weightNum = Integer.parseInt(str2);
        int height1Num = Integer.parseInt(str3);
        int height2Num = Integer.parseInt(str4);
        int inches;
        double cm; //centimeters

        //intent test
       // i.putExtra("qstring",myString);

        //Conversions
        double W = (double) weightNum;  // weight conversion to kg
        W = (W/2.2);

        inches = (12*height1Num) + height2Num;
        cm = (double) inches;
        cm = cm * 2.54;  // centimeter conversion

        if(genderM.isChecked())
        {
            male_calc(ageNum,W,cm);


        }
        if(genderF.isChecked())
        {
            female_calc(ageNum, W, cm);

        }











    }

    private void male_calc(int ageNum, double w, double cm)
    {
        double m_result;
        String m_gender = "male";
        m_result = 66.47+ (13.75*w) + (5*cm) - (6.75*ageNum);

        Intent maleIntent = new Intent (getApplicationContext(),ResultsActivity.class);
        Bundle b = new Bundle();
        b.putDouble("key",m_result);
        b.putString("key2",m_gender);
        maleIntent.putExtras(b);
        startActivity(maleIntent);



       /* Toast t;
        t = Toast.makeText(this, "Male "+m_result, Toast.LENGTH_LONG);
        t.show(); */

    }

    private void female_calc(int ageNum, double w, double cm)
    {
        double f_result;
        String f_gender = "female";
        f_result = 665.09 + (9.56*w) + (1.84*cm) - (4.67*ageNum);


        Intent femaleIntent = new Intent (getApplicationContext(),ResultsActivity.class);
        Bundle b = new Bundle();
        b.putDouble("key",f_result);
        b.putString("key2",f_gender);
        femaleIntent.putExtras(b);
        startActivity(femaleIntent);

       /* Toast t;
        t = Toast.makeText(this, "Female "+f_result,Toast.LENGTH_LONG);
        t.show();
        */

    }


}

