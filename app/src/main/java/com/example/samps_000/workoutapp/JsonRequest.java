package com.example.samps_000.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonRequest extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("results", "made it here6");
        Bundle addData = getIntent().getExtras();
        if(addData == null)
        {
            return;
        }
        else
        {
            String search = addData.getString("search");
            Log.d("results", search);
        }

        DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
        HttpPost httppost = new HttpPost("http://api.nal.usda.gov/ndb/search/?format=json&q=butter&sort=n&max=25&offset=0&api_key=vdMQ5GuTHv1uDeZiOiu7kAIpTfIP9u7J35J5U6R9");
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
        } catch (Exception e) {
            // Oops
        } finally {
            try {

                Log.d("results", "made it here11");
                if (inputStream != null) inputStream.close();
            } catch (Exception squish) {
            }
        }

        Log.d("results", "made it here12");
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("result", result);
        Log.d("results", "made it here13");
        startActivity(i);
    }
}
