package com.company.Entities;

public class Like {
    private Integer likeId, likedByUserId, likedVideoId, likedCommentId;

    public Like(Integer likeId, Integer likedByUserId, Integer likedVideoId, Integer likedCommentId) {
        this.likeId = likeId;
        this.likedByUserId = likedByUserId;
        this.likedVideoId = likedVideoId;
        this.likedCommentId = likedCommentId;
    }

    @Override
    public String toString() {
        return "Like{" +
                "likeId=" + likeId +
                ", likedByUserId=" + likedByUserId +
                ", likedVideoId=" + likedVideoId +
                ", likedCommentId=" + likedCommentId +
                '}';
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

}

