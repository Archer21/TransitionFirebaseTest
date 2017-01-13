package com.archer.transitionfirebasetest.util;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

/**
 * Created by alanaliaga on 13/1/17.
 */

public class Helpers {

    public static void navigate (Activity currentActivity, Class targetActivity) {
        Intent intent = new Intent(currentActivity, targetActivity);
        currentActivity.startActivity(intent);
    }

    public static void replaceFragment (FragmentManager fm, int containerLayout, Fragment fragment) {
        fm.beginTransaction().replace(containerLayout, fragment).commit();
    }
}


























