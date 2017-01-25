package com.archer.transitionfirebasetest.io;

import com.archer.transitionfirebasetest.io.adapter.PostTypeAdapter;
import com.archer.transitionfirebasetest.io.response.PostResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alanaliaga on 25/1/17.
 */

public class FirebaseApiAdapter {

    private static FirebaseApiService API_SERVICE;

    public static FirebaseApiService getApiService () {
        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                     .baseUrl(FirebaseApiConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(buildGsonConverterFactory()))
                    .build();

            API_SERVICE = retrofit.create(FirebaseApiService.class);
        }

        return API_SERVICE;
    }


    private static Gson buildGsonConverterFactory () {
        Gson builder = new GsonBuilder()
                .registerTypeAdapter(PostResponse.class, new PostTypeAdapter())
                .create();

        return builder;
    }
}





























