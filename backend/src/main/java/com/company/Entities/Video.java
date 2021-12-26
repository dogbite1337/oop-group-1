package com.company.Entities;

public class Video {
    @Override
    public String toString() {
        return "Video{" +
                "videoId=" + videoId +
                ", userId=" + userId +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Video(Integer videoId, Integer userId, String link, String title, String description) {
        this.videoId = videoId;
        this.userId = userId;
        this.link = link;
        this.title = title;
        this.description = description;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    private Integer videoId, userId;
    private String link, title, description;
}
