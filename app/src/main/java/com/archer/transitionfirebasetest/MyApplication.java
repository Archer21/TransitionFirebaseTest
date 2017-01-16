package com.archer.transitionfirebasetest;

import android.app.Application;
import android.content.Context;

import com.archer.transitionfirebasetest.util.Constants;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by archer on 16-01-17.
 */

public class MyApplication extends Application {
    DatabaseReference postReference;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);

        postReference = firebaseDatabase.getReference(Constants.FIREBASE_DATABASE_LOCATION_POST);
    }

    public DatabaseReference getPostReference () {
        return postReference;
    }

    public Context getContext () {
        return context;
    }
}
































