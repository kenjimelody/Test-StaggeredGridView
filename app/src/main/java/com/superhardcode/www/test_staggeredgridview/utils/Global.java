package com.superhardcode.www.test_staggeredgridview.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

/**
 * Created by Melody on 7/13/2016 AD.
 */
public class Global {

    public static int getScreenWidth(Context context) {

        DisplayMetrics metrics = new DisplayMetrics();
        ((AppCompatActivity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.widthPixels;
    }
}
