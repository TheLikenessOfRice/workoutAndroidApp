package com.calcalc.samps_000.workoutapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class FoodInfo extends Activity{

    String foodNom;
    String api = "http://api.nal.usda.gov/ndb/nutrients/?format=json&api_key=vdMQ5GuTHv1uDeZiOiu7kAIpTfIP9u7J35J5U6R9&ndbno=0000&nutrients=205&nutrients=204&nutrients=208&nutrients=269&nutrients=203&nutrients=291&nutrients=301&nutrients=307&nutrients=306";

    HashMap<String, String> log_items;
    ArrayList<HashMap<String, String>> full_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);

        Bundle foodData = getIntent().getExtras();
        if(foodData == null)
        {
            return;
        }
        else
        {
            foodNom = foodData.getString("no");
        }
        new getClient().execute();
    }

    protected class getClient extends AsyncTask<Void, Void, Void>{

        ProgressDialog pDialog;
        boolean internetConnection = true;
        JSONObject jObject;
        JSONArray nutrients;
        String servingSize;
        String sugars;
        String fat;
        String carbs;
        String calories;
        String name;
        String protein;
        String calcium;
        String potassium;
        String sodium;
        String fiber;
        String servingWeight;
        float sugarsG;
        float proteinG;
        float carbsG;
        float caloriesG;
        float potassiumG;
        float fatG;
        float calciumG;
        float sodiumG;
        float fiberG;
        float init_serv_g;
        boolean isEmpty = false;
        Spinner servSizeDropDown = (Spinner) findViewById(R.id.measurementDropDown);
        ArrayList<String> measurements = new ArrayList<>();


        private boolean isNetworkAvailable() {
            Log.d("results", "internet1");
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            Log.d("results", "internet2");

            Log.d("results", "NetworkInfo: " + activeNetworkInfo);
            if(activeNetworkInfo == null)
            {
                return false;
            }
            Log.d("results", "IsConnecting: " + activeNetworkInfo.isConnectedOrConnecting());
            return activeNetworkInfo.isConnectedOrConnecting();

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            api = api.replace("0000", foodNom);
            pDialog = new ProgressDialog(FoodInfo.this);
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            if(isNetworkAvailable()) {

                Log.d("results", "internet true");

                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost(api);
                // Depends on your web service

                Log.d("results", "made it here8");
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;

                Log.d("results", "made it here9");
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }

                    Log.d("results", "made it here10");
                    result = sb.toString();
                } catch (Exception e)
                {
                    Log.e("results", "exception", e);
                }
                finally
                {
                    try {

                        Log.d("results", "made it here11");
                        if (inputStream != null) inputStream.close();
                    } catch (Exception squish) {
                        Log.e("results", "exception", squish);
                    }

                }

                try {
                    jObject = new JSONObject(result);
                    JSONObject list = jObject.getJSONObject("report");
                    nutrients = list.getJSONArray("foods");
                    Log.d("results", nutrients.toString());
                    if (nutrients.length() > 0) {


                        Log.d("results", "Len: " + String.valueOf(nutrients.length()));
//ERROR HERE
                        JSONObject item = nutrients.getJSONObject(0);

                        servingSize = item.getString("measure");
                        name = item.getString("name");
                        Log.d("results", "made it hereb");
                        servingWeight = item.getString("weight");
                        Log.d("results", "made it here: " + servingWeight);
                        init_serv_g = Float.parseFloat(servingWeight);
                        servingWeight += " grams";
                        Log.d("results", "made it herec");

                        Log.d("results", "made it herea");

                        measurements.add(servingSize);
                        measurements.add(servingWeight);
                        measurements.add("100 grams");



                        JSONArray foodArray = item.getJSONArray("nutrients");

                        for(int i = 0; i < foodArray.length();i++)
                        {
                            JSONObject itemObj = foodArray.getJSONObject(i);
                            int nutrientNo = Integer.parseInt(itemObj.getString("nutrient_id"));
                            Log.d("results", String.valueOf(nutrientNo));
                            switch(nutrientNo) {
                                case 203:
                                    protein = itemObj.getString("value");
                                    protein += itemObj.getString("unit");
                                    break;

                                case 204:
                                    fat = itemObj.getString("value");
                                    fat += itemObj.getString("unit");
                                    break;

                                case 205:
                                    carbs = itemObj.getString("value");
                                    carbs += itemObj.getString("unit");
                                    break;

                                case 301:
                                    calcium = itemObj.getString("value");
                                    calcium += itemObj.getString("unit");
                                    break;

                                case 306:
                                    potassium = itemObj.getString("value");
                                    potassium += itemObj.getString("unit");
                                    break;

                                case 269:
                                    Log.d("results", "made to sugars");
                                    sugars = itemObj.getString("value");
                                    sugars += itemObj.getString("unit");
                                    break;

                                case 307:
                                    sodium = itemObj.getString("value");
                                    sodium += itemObj.getString("unit");
                                    break;

                                case 208:
                                    calories = itemObj.getString("value");
                                    break;

                                case 291:
                                    fiber = itemObj.getString("value");
                                    fiber += itemObj.getString("unit");
                                    break;

                                default:
                                    Log.d("results", "no match for nutrient num");
                                    break;
                            }
                        }
                    }
                    else
                    {
                        isEmpty = true;
                    }

                }catch (JSONException e) {
                    Log.e("results", "exception", e);

                }
                Log.d("results", "made it here12");
                if (nutrients == null) {
                    Log.d("results", "foods is null");
                }
            }
            else
            {
                internetConnection = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(pDialog.isShowing())
                pDialog.dismiss();

            if(isEmpty) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FoodInfo.this);
                builder.setMessage("No Information Found For This Item")
                        .setTitle("No Data")
                        .setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("results", "dialogReturn");
                        finish();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

            if(!internetConnection){
                AlertDialog.Builder builder = new AlertDialog.Builder(FoodInfo.this);
                builder.setMessage("No Internet Connection Detected")
                        .setTitle("No Connection")
                        .setCancelable(false);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(FoodInfo.this,
                    android.R.layout.simple_spinner_item, measurements);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            servSizeDropDown.setAdapter(adapter);
            TextView foodName = (TextView) findViewById(R.id.foodName);
            TextView serving = (TextView) findViewById(R.id.servingSize);

            final TextView sugarText = (TextView) findViewById(R.id.sugar);

            final TextView proteinText = (TextView) findViewById(R.id.protein);

            final TextView carbText = (TextView) findViewById(R.id.carbs);

            final TextView caloriesText = (TextView) findViewById(R.id.calories);

            final TextView potassiumText = (TextView) findViewById(R.id.potassium);

            final TextView fatsText = (TextView) findViewById(R.id.fats);

            final TextView calciumText = (TextView) findViewById(R.id.calcium);

            final TextView sodiumText = (TextView) findViewById(R.id.sodium);

            final TextView fiberText = (TextView) findViewById(R.id.fiber);

            foodName.setText(name);
            foodName.setMovementMethod(new ScrollingMovementMethod());

            final TextView[] nutTexts = {sugarText, proteinText, carbText, fatsText, fiberText, potassiumText, sodiumText, calciumText};
            final Float[] GramsG = {sugarsG, proteinG, carbsG, fatG, fiberG};
            final String[] Grams = {sugars, protein, carbs, fat, fiber};
            final Float[] MG = {potassiumG, sodiumG, calciumG};
            final String[] MGs = {potassium, sodium, calcium};

            //set inital nutrient values
            for(int i = 0; i < nutTexts.length; i++){
               if(i < Grams.length) {
                   nutTexts[i].setText(Grams[i]);
               }
               else{
                   nutTexts[i].setText(MGs[i-5]);
               }
            }

            servSizeDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int item = servSizeDropDown.getSelectedItemPosition();

                    //convert to 100 grams
                    if (item == 2) {

                        for(int i = 0; i < Grams.length; i++){
                            Log.d("results", Integer.toString(i));

                            if(!"--".equals(Grams[i].substring(0, Grams[i].length()-1))) {
                                GramsG[i] = (Float.parseFloat(Grams[i].substring(0, Grams[i].length() - 1)) / init_serv_g) * 100;
                            }


                            if(i < MG.length) {
                                if (!"--".equals(MGs[i].substring(0, MGs[i].length() - 1))) {

                                    MG[i] = (Float.parseFloat(MGs[i].substring(0, MGs[i].length() - 2)) / init_serv_g) * 100;
                                }
                            }

                            Log.d("results", Grams[i].toString());
                        }
                        caloriesG = (Float.parseFloat(calories) / init_serv_g) * 100;

                        //set text to new values
                        for(int i = 0; i < nutTexts.length;i++){

                            if(i < GramsG.length) {
                                if(String.valueOf(GramsG[i]).equals("0.0")){
                                    nutTexts[i].setText("--g");
                                }
                                else {
                                    String temp = String.valueOf(GramsG[i]);
                                    int decimal = temp.indexOf('.');
                                    nutTexts[i].setText(temp.substring(0, decimal+2) + "g");
                                }
                            }
                            else{
                                if(String.valueOf(MG[i-5]).equals("0.0")){
                                    nutTexts[i].setText("--mg");
                                }
                                else {
                                    String temp = String.valueOf(MG[i - 5]);
                                    int decimal = temp.indexOf('.');
                                    nutTexts[i].setText(temp.substring(0,decimal+3) + "mg");
                                }
                            }
                        }
                        String temp = String.valueOf(caloriesG);
                        int decimal = temp.indexOf('.');
                        caloriesText.setText(temp.substring(0, decimal+3));
                    }

                    //reset to original nutrient values
                    else if(item == 0 || item == 1){
                        for(int i = 0; i < nutTexts.length; i++){
                            if(i < Grams.length) {
                                nutTexts[i].setText(Grams[i]);
                            }
                            else{
                                nutTexts[i].setText(MGs[i-5]);
                            }
                        }
                        caloriesText.setText(calories);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void logAddClick(View v){

        TextView titleText = (TextView) findViewById(R.id.foodName);
        TextView calories = (TextView) findViewById(R.id.calories);

        Log.d("addLog", "TT: " + titleText.getText().toString());
        Log.d("addLog", "C: " + calories.getText().toString());
        Log.d("addLog", "FN: " + foodNom);

        SharedPreferences prefs = getSharedPreferences("logData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        int i = 1;
        while(null != prefs.getString(String.valueOf(i) + " title", null)) {
            i+=1;
        }
        editor.putString(String.valueOf(i) + " title", titleText.getText().toString());
        editor.putString(String.valueOf(i) + " cal", calories.getText().toString());
        editor.putString(String.valueOf(i) + " no", foodNom);
        editor.putString(String.valueOf(i) + " keyNum", String.valueOf(i)) ;

        editor.apply();
        Toast.makeText(FoodInfo.this, "Added to Log!", Toast.LENGTH_LONG).show();
    }


    public void goToLogClick(View view){
        Intent i = new Intent(this, FoodLog.class);
        startActivity(i);
    }

    public void menuButtonClick(View view){
        Intent i = new Intent(this, StartPage.class);
        startActivity(i);
    }
}
