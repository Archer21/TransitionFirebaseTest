package com.archer.transitionfirebasetest.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.archer.transitionfirebasetest.R;
import com.archer.transitionfirebasetest.domain.Post;
import com.archer.transitionfirebasetest.view.activity.PostDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by archer on 11-01-17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Activity ACTIVITY;
    private List<Post> posts;
    private int resource;


    public void addAll (List<Post> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    public PostAdapter(Activity activity, int resource) {
        this.ACTIVITY = activity;
        this.resource = resource;
        this.posts    = new ArrayList<>();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ACTIVITY).inflate(resource, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post currentPost = posts.get(position);
        holder.setUsername(currentPost.getUsername());
        holder.setCollection(currentPost.getCommunity());
        holder.setContent(currentPost.getContent());
        holder.setAvatar(currentPost.getAvatar());



        if (currentPost.getUrlImage() != null) {
            holder.setImage(currentPost.getUrlImage());
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ACTIVITY, PostDetailActivity.class);
                ACTIVITY.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void addPost(Post post) {
        posts.add(post);
        notifyDataSetChanged();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.avatar_post)
        CircleImageView avatar;
        @BindView(R.id.username_post)
        TextView username;
        @BindView(R.id.collection)
        TextView collection;
        @BindView(R.id.content_post)
        TextView content;
        @BindView(R.id.image_post)
        ImageView image;


        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setAvatar (String url) {
            if (url != null) {
                Picasso.with(ACTIVITY).load(url).into(avatar);
            } else {
                Picasso.with(ACTIVITY).load(R.drawable.avatar_placeholder).into(avatar);
            }
        }

        public void setUsername (String username) {
            this.username.setText(username);
        }

        public void setCollection (String collection) {
            this.collection.setText(collection);
        }

        public void setContent (String content) {
            this.content.setText(content);
        }

        public void setImage (String url) {
            Picasso.with(ACTIVITY).load(url).into(image);
        }
    }
}





































