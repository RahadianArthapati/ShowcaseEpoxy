package com.grandline.showcaseepoxy.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by home on 9/4/17.
 */

public class ScreenUtils {
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        Log.d(ScreenUtils.class.getSimpleName(),String.valueOf(dpWidth));
        int noOfColumns = (int) (dpWidth / 120);
        return noOfColumns;
    }
}
