package com.calcalc.samps_000.workoutapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by samps_000 on 10/20/2015.
 */
public class NavAdapter extends ArrayAdapter<String>{

    int listItem;

    public NavAdapter(Context context, int resource, String[] objects, int selectedItem) {
        super(context, resource, objects);
        listItem = selectedItem;
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

    public static void close(DrawerLayout mDrawerLayout){
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    public static void drawerListener(final Activity currentActivity, final int selectedItem) {
        Log.d("nav", "here");
        ListView mDrawerList = (ListView) currentActivity.findViewById(R.id.navList);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String activity = (String) parent.getItemAtPosition(position);
                Log.d("nav", "S: " + activity);
                DrawerLayout mDrawerLayout = (DrawerLayout) currentActivity.findViewById(R.id.drawer_layout);
                if (activity.equals("Main")) {
                    NavAdapter.close(mDrawerLayout);
                    
                    if(selectedItem != 0) {
                        Intent i = new Intent(currentActivity, StartPage.class);
                        currentActivity.startActivity(i);
                    }
                }else if (activity.equals("Search")) {
                    NavAdapter.close(mDrawerLayout);

                    if(selectedItem != 1) {
                        Intent i = new Intent(currentActivity, MainActivity.class);
                        currentActivity.startActivity(i);
                    }
                }else if (activity.equals("Log")) {
                    NavAdapter.close(mDrawerLayout);

                    if(selectedItem != 2) {
                        Intent i = new Intent(currentActivity, FoodLog.class);
                        currentActivity.startActivity(i);
                    }
                } else if (activity.equals("Profile")) {
                    NavAdapter.close(mDrawerLayout);

                    if(selectedItem != 3) {
                        Intent i = new Intent(currentActivity, Profile.class);
                        currentActivity.startActivity(i);
                    }
                } else if (activity.equals("Calculate")) {
                    NavAdapter.close(mDrawerLayout);

                    if(selectedItem != 4) {
                        Intent i = new Intent(currentActivity, CalcActivity.class);
                        currentActivity.startActivity(i);
                    }
                }
            }
        });
    }
}
