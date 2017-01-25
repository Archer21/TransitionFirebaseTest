package com.archer.transitionfirebasetest.io.adapter;

import com.archer.transitionfirebasetest.domain.Post;
import com.archer.transitionfirebasetest.io.model.PostKeys;
import com.archer.transitionfirebasetest.io.response.PostResponse;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alanaliaga on 25/1/17.
 */

public class PostTypeAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter out, Object value) throws IOException {

    }

    @Override
    public PostResponse read(JsonReader in) throws IOException {
        PostResponse response = new PostResponse();
        List<Post> postList = new ArrayList<>();

        in.beginObject();

        while (in.hasNext()) {
            Post post = null;

            try {
                post = readPost(in);
            } catch (Exception e) {
                e.printStackTrace();
            }

            postList.add(post);
        }

        in.endObject();
        response.setPostList(postList);

        return response;

    }


    private Post readPost (JsonReader reader) throws Exception {
        Post post = new Post();
        reader.nextName();
        reader.beginObject();

        while (reader.hasNext()) {
            String next = reader.nextName();
            switch (next) {
                case PostKeys.COMMUNITY:
                    post.setCommunity(reader.nextString());
                    break;
                case PostKeys.CONTENT:
                    post.setContent(reader.nextString());
                    break;
                case PostKeys.LIKES:
                    post.setLikes(reader.nextInt());
                    break;
                case PostKeys.UID:
                    post.setUid(reader.nextString());
                    break;
                case PostKeys.URL_IMAGE:
                    post.setUrlImage(reader.nextString());
                    break;
                case PostKeys.USERNAME:
                    post.setUsername(reader.nextString());
                    break;
            }
        }

        reader.endObject();
        return post;
    }
}





























