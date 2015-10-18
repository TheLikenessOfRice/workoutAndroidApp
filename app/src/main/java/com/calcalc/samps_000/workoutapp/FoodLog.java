package com.calcalc.samps_000.workoutapp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FoodLog extends ListActivity implements AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener{
    ArrayList<Set<String>> foodItems = new ArrayList<Set<String>>();
    List<HashMap<String, String>> cur_list = new ArrayList<HashMap<String, String>>();
    ListAdapter adapter;
    ListView lv;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Log.d("log", "clickHere");
        lv = getListView();
        lv.setTextFilterEnabled(true);
        lv.setOnItemLongClickListener(FoodLog.this);
        lv.setOnItemClickListener(FoodLog.this);
        init();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.d("log", "clickHere2");
        HashMap<String, String> food = (HashMap<String, String>) parent.getItemAtPosition(position);
        String foodItem = food.get("no");
        Intent i = new Intent(FoodLog.this, FoodInfo.class);
        i.putExtra("no", foodItem);
        startActivity(i);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        Log.d("log", "clickHere3");
        HashMap<String, String> food = (HashMap<String, String>) parent.getItemAtPosition(position);
        final String key = food.get("key");
        Log.d("log", "K: " + key);
        AlertDialog.Builder adb = new AlertDialog.Builder(FoodLog.this);
        adb.setTitle("Delete?");
        adb.setMessage("Are you sure you want to delete this item?");
        adb.setNegativeButton("Cancel", null);
        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor remove = FoodLog.this.getSharedPreferences("logData", Context.MODE_PRIVATE).edit();
                remove.remove(key + " title");
                remove.remove(key + " cal");
                remove.remove(key + " no");
                remove.remove(key + " keyNum");
                remove.commit();
                cur_list.remove(position);
                ((BaseAdapter) adapter).notifyDataSetChanged();
            }
        });
        adb.show();

        return true;
    }
    private void init(){
        SharedPreferences prefs = this.getSharedPreferences("logData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        int i = 1;
        while(null != prefs.getString(String.valueOf(i) + " title", null)){
            HashMap<String, String>  item = new HashMap<String, String>();
            String title = prefs.getString(String.valueOf(i) + " title", null);
            String cal = prefs.getString(String.valueOf(i) + " cal", null);
            String no = prefs.getString(String.valueOf(i) + " no", null);
            String key = prefs.getString(String.valueOf(i) + " keyNum", null);
            item.put("title", title);
            item.put("cal", cal);
            item.put("no", no);
            item.put("key", key);
            Log.d("saves", "T: " + title);
            Log.d("saves", "C: " + cal);
            Log.d("saves", "N: " + no);
            Log.d("saves", "K: " + key);
            cur_list.add(item);
            i += 1;

        }

        Log.d("saves", "here5");

        String[] from = new String[] {"title", "cal", "no"};
        int[] to = new int[] {R.id.logFoodTitle, R.id.logFoodCal};
        adapter = new SimpleAdapter(this, cur_list, R.layout.log_list_item, from, to);
        setListAdapter(adapter);

    }


}
