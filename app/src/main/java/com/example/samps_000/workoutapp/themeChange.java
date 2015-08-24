package com.example.samps_000.workoutapp;

import android.app.Activity;
import android.content.Intent;

public class themeChange {
    private static int cTheme;
    public final static int DARK = 0;
    public final static int LIGHT = 1;
    public static void changeToTheme(Activity activity, int theme) {
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch(cTheme){
            case DARK:
                activity.setTheme(R.style.DarkTheme);
                break;
            case LIGHT:
                activity.setTheme(R.style.LightTheme);
                break;
        }
    }
}
