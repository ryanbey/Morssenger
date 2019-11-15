package com.goodfellows.morssenger;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;

import androidx.core.content.ContextCompat;

public class Utils {
    // Change the status bar from white to green for certain activities
    public static void greenStatusBar(Activity activity, int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(
                      ContextCompat.getColor(activity, color));
        }
    }

    // Code to darken the color supplied (mostly color of toolbar)
    private static int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= 1.0f;
        return Color.HSVToColor(hsv);
    }
}
