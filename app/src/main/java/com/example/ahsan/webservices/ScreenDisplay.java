package com.example.ahsan.webservices;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Display;

public class ScreenDisplay {
    private Activity activity;
    private float dpHeight;
    private float dpWidht;

    public ScreenDisplay(Activity activity){
        this.activity = activity;

        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outmetrics = new DisplayMetrics();
        display.getMetrics(outmetrics);

        float density = activity.getResources().getDisplayMetrics().density;
        dpHeight = outmetrics.heightPixels/density;
        dpWidht = outmetrics.widthPixels/density;
    }

    public float getDpHeight() { return dpHeight; }

    public float getDpWidht() { return  dpWidht; }
}
