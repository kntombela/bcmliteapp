package com.example.android.bcmliteapp.utils;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by keket on 26/11/2017.
 */

public class ProgressBarUtil {

    private static ProgressBar mProgressBar;

    public static void showProgressBar(Context context) {
        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleSmall);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    public static void closeProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

}
