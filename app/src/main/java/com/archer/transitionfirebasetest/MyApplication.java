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
    DatabaseReference postsReference;
    DatabaseReference usersReference;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);

        postsReference = firebaseDatabase.getReference(Constants.FIREBASE_DATABASE_LOCATION_POSTS);
        usersReference = firebaseDatabase.getReference(Constants.FIREBASE_DATABASE_LOCATION_USERS);
    }

    public DatabaseReference getPostsReference () {
        return postsReference;
    }
    public DatabaseReference getUsersReference () {
        return usersReference;
    }


    public Context getContext () {
        return context;
    }
}
































