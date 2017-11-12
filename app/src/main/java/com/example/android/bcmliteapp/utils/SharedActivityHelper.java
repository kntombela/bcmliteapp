package com.example.android.bcmliteapp.utils;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by keket on 11/11/2017.
 */


public class SharedActivityHelper {

    //    Sets the toolbar for the an activity
    public static void setActivityToolBar(AppCompatActivity activity, Toolbar toolBar) {
        activity.setSupportActionBar(toolBar);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

}
