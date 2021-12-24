package com.company.DTOs;

import com.company.Entities.Video;

import java.util.ArrayList;

public class UserWithoutPassword {
    private Integer userId;
    private String username, description;
    private ArrayList<Video> userVideos = new ArrayList<Video>();
    public UserWithoutPassword(Integer userId, String username, String description, ArrayList<Video> userVideos){
        this.userId = userId;
        this.username = username;
        this.description = description;
        this.userVideos = userVideos;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserWithoutPassword{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", userVideos=" + userVideos +
                '}';
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
}
