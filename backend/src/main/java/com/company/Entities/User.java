package com.company.Entities;

import java.util.ArrayList;

public class User {
    private Integer userId = 0, videosPosted = 0, subscribers = 0;
    private String username, password, description, profileURL;
    private ArrayList<Video> userVideos = new ArrayList<Video>();
    private ArrayList<SearchHistory> userSearchHistories = new ArrayList<SearchHistory>();


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


    public Integer getVideosPosted() {
        return videosPosted;
    }

    public void setVideosPosted(Integer videosPosted) {
        this.videosPosted = videosPosted;
    }

    public Integer getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Integer subscribers) {
        this.subscribers = subscribers;
    }

    public ArrayList<SearchHistory> getUserSearchHistories() {
        return userSearchHistories;
    }

    public void setUserSearchHistories(ArrayList<SearchHistory> userSearchHistories) {
        this.userSearchHistories = userSearchHistories;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", videosPosted=" + videosPosted +
                ", subscribers=" + subscribers +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", profileURL='" + profileURL + '\'' +
                ", userVideos=" + userVideos + '\'' +
                ", userSearchHistories=" + userSearchHistories + '\'' +
                '}';
    }

    public User(Integer userId, String username, String password, String description, String profileURL, Integer subscribers, Integer videosPosted){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.description = description;
        this.profileURL = profileURL;
        this.subscribers = subscribers;
        this.videosPosted = videosPosted;
    }

    public User(Integer userId, String username, String description, String profileURL, Integer subscribers, Integer videosPosted, ArrayList<Video> userVideos){
        this.userId = userId;
        this.username = username;
        this.description = description;
        this.profileURL = profileURL;
        this.subscribers = subscribers;
        this.videosPosted = videosPosted;
        this.userVideos = userVideos;
    }
}
