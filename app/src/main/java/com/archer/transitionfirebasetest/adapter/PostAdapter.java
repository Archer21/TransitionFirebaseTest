package com.archer.transitionfirebasetest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archer.transitionfirebasetest.domain.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by archer on 11-01-17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context CONTEXT;
    private List<Post> posts;
    private int resource;


    public PostAdapter(Context CONTEXT, int resource) {
        this.CONTEXT  = CONTEXT;
        this.resource = resource;
        this.posts    = new ArrayList<>();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(CONTEXT).inflate(resource, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post currentPost = posts.get(position);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {



        public PostViewHolder(View itemView) {
            super(itemView);
        }
    }
}





































