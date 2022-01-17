package com.company.DTOs;

public class UserWithoutPassword {
    private Integer userId, videosPosted, subscribers;
    private String username, description, profileURL;


    public UserWithoutPassword(Integer userId, String username, String description, String profileURL, Integer videosPosted, Integer subscribers) {
        this.userId = userId;
        this.username = username;
        this.description = description;
        this.profileURL = profileURL;
        this.subscribers = subscribers;
        this.videosPosted = videosPosted;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    @Override
    public String toString() {
        return "UserWithoutPassword{" +
                "userId=" + userId +
                ", videosPosted=" + videosPosted +
                ", subscribers=" + subscribers +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", profileURL='" + profileURL + '\'' +
                '}';
    }


}
