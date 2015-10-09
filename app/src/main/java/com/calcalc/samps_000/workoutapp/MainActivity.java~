package com.example.samps_000.workoutapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
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


public class MainActivity extends ListActivity {


    ArrayList<HashMap<String, String>> foodItems;
    JSONArray foods;
    JSONObject jObject;
    String api = "http://api.nal.usda.gov/ndb/search/?format=json&q=butter&sort=r&max=25&offset=0&api_key=vdMQ5GuTHv1uDeZiOiu7kAIpTfIP9u7J35J5U6R9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("restart", "searchrestart");
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text
                //String food = String.valueOf(parent.getItemAtPosition(position));
                HashMap<String, String> food = (HashMap<String, String>) parent.getItemAtPosition(position);
                String foodItem = food.get("no");
                Intent i = new Intent(MainActivity.this, FoodInfo.class);
                i.putExtra("no", foodItem);
                startActivity(i);
            }
        });

    }

    public void searchButtonClicked(View view) {
        api = "http://api.nal.usda.gov/ndb/search/?format=json&q=butter&sort=r&max=25&offset=0&api_key=vdMQ5GuTHv1uDeZiOiu7kAIpTfIP9u7J35J5U6R9";
        Log.d("results", "searchButtonClicked");
        foods = null;
        jObject = null;
        foodItems = new ArrayList<HashMap<String, String>>();
        EditText search = (EditText) findViewById(R.id.getFood);
        String searchItem;
        searchItem = search.getText().toString();
        searchItem = searchItem.replaceAll(" ", "_");
        searchItem = searchItem.replaceAll("\\s", "");
        Log.d("results", "item: " + searchItem);
        api = api.replace("butter", searchItem);
        Log.d("results", "api: " + api);
        View newView = this.getCurrentFocus();
        if (newView != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        new CallClient().execute();
    }

    protected class CallClient extends AsyncTask<Void, Void, Void> {
        boolean internetConnection = true;
        ProgressDialog pDialog;
        String[] from = new String[]{"group", "name"};
        int[] to = new int[]{R.id.groupText, R.id.nameText};


        private boolean isNetworkAvailable() {
            Log.d("results", "internet1");
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            Log.d("results", "internet2");

            Log.d("results", "NetworkInfo: " + activeNetworkInfo);
            if (activeNetworkInfo == null) {
                return false;
            }
            Log.d("results", "IsConnecting: " + activeNetworkInfo.isConnectedOrConnecting());
            return activeNetworkInfo.isConnectedOrConnecting();

        }


        @Override
        protected void onPreExecute() {
            Log.d("results", "internet3");
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Searching...");
            pDialog.setCancelable(false);
            pDialog.show();
            Log.d("results", "internet4");
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            if (isNetworkAvailable()) {

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
                } catch (
                        Exception e
                        )

                {
                    // Oops
                } finally

                {
                    try {

                        Log.d("results", "made it here11");
                        if (inputStream != null) inputStream.close();
                    } catch (Exception squish) {
                    }

                }

                try {
                    //Log.d("results", "results: " + result);
                    jObject = new JSONObject(result);
                    JSONObject list = jObject.getJSONObject("list");
                    foods = list.getJSONArray("item");
                    //Log.d("results", "here" + jObject.toString(2));
                    for (int i = 0; i < foods.length(); i++) {
                        JSONObject item = foods.getJSONObject(i);
                        String group = item.getString("group");
                        String name = item.getString("name");
                        String no = item.getString("ndbno");
                        Log.d("foods" + String.valueOf(i), group + " " + name + " " + no);
                        HashMap<String, String> food = new HashMap<String, String>();
                        food.put("group", group);
                        food.put("name", name);
                        food.put("no", no);
                        Log.d("hashmap", food.get("group"));
                        Log.d("hashmap", food.get("name"));
                        Log.d("hashmap", food.get("no"));
                        foodItems.add(food);
                    }
                } catch (JSONException e) {
                    Log.e("results", "exception", e);

                }
                Log.d("results", "made it here12");
                if (foods == null) {
                    Log.d("results", "foods is null");
                }
            } else {
                internetConnection = false;
            }


            //ListAdapter searchDisplay = new CustomAdapter(this, foodItems);
            //searchListView.setAdapter(searchDisplay);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (pDialog.isShowing())
                pDialog.dismiss();
            if (!internetConnection) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("No Internet Connection Detected")
                        .setTitle("No Connection")
                        .setCancelable(false);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                Log.d("results", "made it here13");
                DisplayList displayList = new DisplayList(from, to);
            }
        }

    }

    public class DisplayList {

        String[] from;
        int[] to;

        public DisplayList(String[] from, int[] to) {

            Log.d("results", "made it here14");
            this.from = from;
            this.to = to;
            onCreateHelper();
        }


        protected void onCreateHelper() {
            Log.d("results", "made it here15");
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, foodItems, R.layout.list_layout, from, to);
            setListAdapter(adapter);
            Log.d("results", "made it here16");
        }
    }
}

