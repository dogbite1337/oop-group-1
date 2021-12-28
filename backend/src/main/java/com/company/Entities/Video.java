package com.company.Entities;

public class Video {


    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId=" + videoId +
                ", userId=" + userId +
                ", views=" + views +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", postedByUsername='" + postedByUsername + '\'' +
                '}';
    }

    public String getPostedByUsername() {
        return postedByUsername;
    }

    public void setPostedByUsername(String postedByUsername) {
        this.postedByUsername = postedByUsername;
    }

    public Video(Integer videoId, Integer userId, String link, String title, String description, Integer views, String postedByUsername) {
        this.videoId = videoId;
        this.userId = userId;
        this.link = link;
        this.title = title;
        this.description = description;
        this.views = views;
        this.postedByUsername = postedByUsername;
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

    private Integer videoId, userId, views;
    private String link, title, description, postedByUsername;
}
