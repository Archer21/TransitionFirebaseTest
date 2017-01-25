package com.archer.transitionfirebasetest.io.response;

import com.archer.transitionfirebasetest.domain.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alanaliaga on 24/1/17.
 */

public class PostResponse {
    private List<Post> postList = new ArrayList<>();

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
































