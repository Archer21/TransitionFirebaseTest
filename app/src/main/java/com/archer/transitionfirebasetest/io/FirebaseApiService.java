package com.archer.transitionfirebasetest.io;

import com.archer.transitionfirebasetest.io.response.PostResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by alanaliaga on 24/1/17.
 */

public interface FirebaseApiService {
    @GET(FirebaseApiConstants.POSTS_PATH)
    Call<PostResponse> getPostList ();
}



























