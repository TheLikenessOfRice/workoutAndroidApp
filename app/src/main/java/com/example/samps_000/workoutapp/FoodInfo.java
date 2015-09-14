package com.example.samps_000.workoutapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

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


public class FoodInfo extends Activity{

    String foodNom;
    String api = "http://api.nal.usda.gov/ndb/nutrients/?format=json&api_key=vdMQ5GuTHv1uDeZiOiu7kAIpTfIP9u7J35J5U6R9&ndbno=0000&nutrients=205&nutrients=204&nutrients=208&nutrients=269&nutrients=203&nutrients=291&nutrients=301&nutrients=307&nutrients=306";

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
        boolean isEmpty = false;


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

                        JSONObject item = nutrients.getJSONObject(0);

                        servingSize = item.getString("measure");
                        servingSize = "Nutrients per " + servingSize;
                        name = item.getString("name");


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
            TextView foodName = (TextView) findViewById(R.id.foodName);
            TextView serving = (TextView) findViewById(R.id.servingSize);
            TextView sugar = (TextView) findViewById(R.id.sugar);

            TextView proteinText = (TextView) findViewById(R.id.protein);

            TextView carbText = (TextView) findViewById(R.id.carbs);

            TextView caloriesText = (TextView) findViewById(R.id.calories);

            TextView potassiumText = (TextView) findViewById(R.id.potassium);

            TextView fatsText = (TextView) findViewById(R.id.fats);

            TextView calciumText = (TextView) findViewById(R.id.calcium);

            TextView sodiumText = (TextView) findViewById(R.id.sodium);

            TextView fiberText = (TextView) findViewById(R.id.fiber);

            foodName.setText(name);
            foodName.setMovementMethod(new ScrollingMovementMethod());

            serving.setText(servingSize);
            sugar.setText(sugars);
            proteinText.setText(protein);
            carbText.setText(carbs);
            caloriesText.setText(calories);
            potassiumText.setText(potassium);
            fatsText.setText(fat);
            calciumText.setText(calcium);
            sodiumText.setText(sodium);
            fiberText.setText(fiber);
        }
    }



}
