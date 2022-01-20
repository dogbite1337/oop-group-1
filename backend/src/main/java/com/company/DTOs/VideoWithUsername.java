package com.company.DTOs;

public class VideoWithUsername {
    private Integer videoId, userId;
    private String videoUrl, title, description, username;

    public VideoWithUsername(Integer videoId, Integer userId, String videoUrl, String title, String description, String username) {
        this.videoId = videoId;
        this.userId = userId;
        this.videoUrl = videoUrl;
        this.title = title;
        this.description = description;
        this.username = username;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setLink(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "VideoWithUsername{" +
                "videoId=" + videoId +
                ", userId=" + userId +
                ", videoUrl='" + videoUrl + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


}
