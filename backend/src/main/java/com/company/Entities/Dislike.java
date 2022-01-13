package com.company.Entities;

public class Dislike {
    private Integer dislikeId, dislikedByUserId, dislikedVideoId, dislikedCommentId;

    public Dislike(Integer dislikeId, Integer dislikedByUserId, Integer dislikedVideoId, Integer dislikedCommentId) {
        this.dislikeId = dislikeId;
        this.dislikedByUserId = dislikedByUserId;
        this.dislikedVideoId = dislikedVideoId;
        this.dislikedCommentId = dislikedCommentId;
    }

    @Override
    public String toString() {
        return "Dislike{" +
                "dislikeId=" + dislikeId +
                ", dislikedByUserId=" + dislikedByUserId +
                ", dislikedVideoId=" + dislikedVideoId +
                ", dislikedCommentId=" + dislikedCommentId +
                '}';
    }

    public Integer getDislikeId() {
        return dislikeId;
    }

    public void setDislikeId(Integer dislikeId) {
        this.dislikeId = dislikeId;
    }

    public Integer getDislikedByUserId() {
        return dislikedByUserId;
    }

    public void setDislikedByUserId(Integer dislikedByUserId) {
        this.dislikedByUserId = dislikedByUserId;
    }

    public Integer getDislikedVideoId() {
        return dislikedVideoId;
    }

    public void setDislikedVideoId(Integer dislikedVideoId) {
        this.dislikedVideoId = dislikedVideoId;
    }

    public Integer getDislikedCommentId() {
        return dislikedCommentId;
    }

    public void setDislikedCommentId(Integer dislikedCommentId) {
        this.dislikedCommentId = dislikedCommentId;
    }

}


