package com.calcalc.samps_000.workoutapp;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

public class themeChange {
    private static int cTheme;
    public final static int THEME_DARK = 0;
    public final static int THEME_LIGHT = 1;
    public static void changeToTheme(Activity activity, int theme) {
        cTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch(cTheme)
        {
            case THEME_DARK:
                activity.setTheme(R.style.DarkTheme);
                break;
            case THEME_LIGHT:
                activity.setTheme(R.style.LightTheme);
                break;
            default:
                Log.d("tests", "default theme");
                break;
        }
    }
}
