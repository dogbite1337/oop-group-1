package com.company.Entities;

public class Dislike {
    private Integer dislikeId, relatesToVideoId, dislikedByUserId, dislikedVideoId, dislikedCommentId;

    public Dislike(Integer dislikeId, Integer relatesToVideoId, Integer dislikedByUserId, Integer dislikedVideoId, Integer dislikedCommentId) {
        this.dislikeId = dislikeId;
        this.relatesToVideoId = relatesToVideoId;
        this.dislikedByUserId = dislikedByUserId;
        this.dislikedVideoId = dislikedVideoId;
        this.dislikedCommentId = dislikedCommentId;
    }

    public Integer getRelatesToVideoId() {
        return relatesToVideoId;
    }

    public void setRelatesToVideoId(Integer relatesToVideoId) {
        this.relatesToVideoId = relatesToVideoId;
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

    @Override
    public String toString() {
        return "Dislike{" +
                "dislikeId=" + dislikeId +
                ", relatesToVideoId=" + relatesToVideoId +
                ", dislikedByUserId=" + dislikedByUserId +
                ", dislikedVideoId=" + dislikedVideoId +
                ", dislikedCommentId=" + dislikedCommentId +
                '}';
    }

}


