package com.calcalc.samps_000.workoutapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;


/**
 * Created by samps_000 on 10/20/2015.
 */
public class NavAdapter extends ArrayAdapter<String>{

    int listItem;
    private static HashMap<String, Class<?>> activities = new HashMap<>();

    public NavAdapter(Context context, int resource, String[] objects, int selectedItem) {
        super(context, resource, objects);
        listItem = selectedItem;
        activities.put("Main", StartPage.class);
        activities.put("Search", MainActivity.class);
        activities.put("Log", FoodLog.class);
        activities.put("Profile", Profile.class);
        activities.put("Calculate", CalcActivity.class);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if(position == listItem) {
            view.setBackgroundColor(Color.parseColor("#F0F0F0"));
            ((TextView) view).setTextColor(Color.parseColor("#7d7d7d"));
        }
       return view;
    }

    public static void close(DrawerLayout mDrawerLayout) {
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    public static void drawerListener(final Activity currentActivity, final int selectedItem) {
        Log.d("nav", "here");
        ListView mDrawerList = (ListView) currentActivity.findViewById(R.id.navList);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final String activity = (String) parent.getItemAtPosition(position);
                Log.d("nav", "S: " + activity);
                DrawerLayout mDrawerLayout = (DrawerLayout) currentActivity.findViewById(R.id.drawer_layout);

                    NavAdapter.close(mDrawerLayout);

                    Log.d("nav", "here1");
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {

                            Log.d("nav", "here3");
                        if (selectedItem != position) {

                            Log.d("nav", "here2");
                            Intent i = new Intent(currentActivity, activities.get(activity));
                            currentActivity.startActivity(i);
                    }
                        }
                    }, 300);
            }
        });

    }

    public static void addDrawerItem(Context currentActivity, int listItem){
        ListView mDrawerList;
        ArrayAdapter mAdapter;
        mDrawerList = (ListView) ((Activity)currentActivity).findViewById(R.id.navList);
        String[] options = {"Main","Search", "Log", "Profile", "Calculate"};
        mAdapter = new NavAdapter(currentActivity, android.R.layout.simple_list_item_1, options, listItem);
        mDrawerList.setAdapter(mAdapter);
    }


}
