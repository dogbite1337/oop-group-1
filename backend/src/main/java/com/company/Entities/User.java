package com.company.Entities;

import java.util.ArrayList;

public class User {
    private Integer userId = 0;
    private String username, password, description, profileURL;
    private ArrayList<Video> userVideos = new ArrayList<Video>();

    public Integer getUserId() {
        return userId;
    }

    public ArrayList<Video> getUserVideos() {
        return userVideos;
    }

    public void setUserVideos(ArrayList<Video> userVideos) {
        this.userVideos = userVideos;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", profileURL='" + profileURL + '\'' +
                ", userVideos=" + userVideos +
                '}';
    }

    public User(Integer userId, String username, String password, String description, String profileURL){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.description = description;
        this.profileURL = profileURL;
    }
}
