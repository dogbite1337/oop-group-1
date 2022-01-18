package com.company.Entities;

public class Video {
    private Integer videoId, userId, views;
    private long uploadDate;
    private String videoURL, title, description, postedByUsername, likes, dislikes, stars;

    public Video(Integer videoId, Integer userId, long uploadDate, String videoURL, String title, String description, Integer views, String postedByUsername, String likes, String dislikes, String stars) {
        this.videoId = videoId;
        this.userId = userId;
        this.uploadDate = uploadDate;
        this.videoURL = videoURL;
        this.title = title;
        this.description = description;
        this.views = views;
        this.postedByUsername = postedByUsername;
        this.likes = likes;
        this.dislikes = dislikes;
        this.stars = stars;
    }

    public Video(Integer videoId, String title, String postedByUsername, String videoURL){
        this.videoId = videoId;
        this.videoURL = videoURL;
        this.title = title;
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

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public long getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(long uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
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

    public String getPostedByUsername() {
        return postedByUsername;
    }

    public void setPostedByUsername(String postedByUsername) {
        this.postedByUsername = postedByUsername;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDislikes() {
        return dislikes;
    }

    public void setDislikes(String dislikes) {
        this.dislikes = dislikes;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId=" + videoId +
                ", userId=" + userId +
                ", views=" + views +
                ", uploadDate=" + uploadDate +
                ", videoURL='" + videoURL + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", postedByUsername='" + postedByUsername + '\'' +
                ", likes='" + likes + '\'' +
                ", dislikes='" + dislikes + '\'' +
                ", stars='" + stars + '\'' +
                '}';
    }
}
