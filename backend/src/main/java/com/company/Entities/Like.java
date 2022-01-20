package com.company.Entities;

public class Like {
    private Integer likeId, relatesToVideoId, likedByUserId, likedVideoId, likedCommentId;

    public Like(Integer likeId, Integer relatesToVideoId, Integer likedByUserId, Integer likedVideoId, Integer likedCommentId) {
        this.likeId = likeId;
        this.relatesToVideoId = relatesToVideoId;
        this.likedByUserId = likedByUserId;
        this.likedVideoId = likedVideoId;
        this.likedCommentId = likedCommentId;
    }

    public Integer getRelatesToVideoId() {
        return relatesToVideoId;
    }

    public void setRelatesToVideoId(Integer relatesToVideoId) {
        this.relatesToVideoId = relatesToVideoId;
    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getLikedByUserId() {
        return likedByUserId;
    }

    public void setLikedByUserId(Integer likedByUserId) {
        this.likedByUserId = likedByUserId;
    }

    public Integer getLikedVideoId() {
        return likedVideoId;
    }

    public void setLikedVideoId(Integer likedVideoId) {
        this.likedVideoId = likedVideoId;
    }

    public Integer getLikedCommentId() {
        return likedCommentId;
    }

    public void setLikedCommentId(Integer likedCommentId) {
        this.likedCommentId = likedCommentId;
    }

    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + likeId +
                ", relatesToVideoId=" + relatesToVideoId +
                ", likedByUserId=" + likedByUserId +
                ", likedVideoId=" + likedVideoId +
                ", likedCommentId=" + likedCommentId +
                '}';
    }

}

