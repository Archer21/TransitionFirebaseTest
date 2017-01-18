package com.archer.transitionfirebasetest.domain;

import java.util.HashMap;

/**
 * Created by archer on 11-01-17.
 */

public class Post {

    private String uid;
    private String avatar;
    private String urlImage;
    private String username;
    private int    likes;
    private String content;
    private String community;
    private HashMap<String, Object> timeStampCreated;

    public Post() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public HashMap<String, Object> getTimeStampCreated() {
        return timeStampCreated;
    }

    public void setTimeStampCreated(HashMap<String, Object> timeStampCreated) {
        this.timeStampCreated = timeStampCreated;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }
}








































