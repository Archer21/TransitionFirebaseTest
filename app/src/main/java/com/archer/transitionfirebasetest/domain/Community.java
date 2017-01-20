package com.archer.transitionfirebasetest.domain;

/**
 * Created by archer on 18-01-17.
 */

public class Community {
    private String name;
    private String members;
    private String author;

    public Community() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
