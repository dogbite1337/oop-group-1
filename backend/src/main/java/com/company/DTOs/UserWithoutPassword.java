package com.company.DTOs;

import com.company.Entities.Video;

import java.util.ArrayList;

public class UserWithoutPassword {
    private Integer userId;
    private String username, description, profileURL;
    private ArrayList<Video> userVideos = new ArrayList<Video>();

    @Override
    public String toString() {
        return "UserWithoutPassword{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", profileURL='" + profileURL + '\'' +
                ", userVideos=" + userVideos +
                '}';
    }

    public UserWithoutPassword(Integer userId, String username, String description, ArrayList<Video> userVideos, String profileURL){
        this.userId = userId;
        this.username = username;
        this.description = description;
        this.userVideos = userVideos;
        this.profileURL = profileURL;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileURL() { return profileURL; }

    public void setProfileURL(String profileURL){ this.profileURL = profileURL; }
}
