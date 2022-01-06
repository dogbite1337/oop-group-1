package com.company.Entities;

import java.sql.Timestamp;

public class SearchHistory {
    private Integer historyId, userId;
    private String keyWord;
    private String time;

    public SearchHistory() {
    }

    public SearchHistory(Integer userId, String keyWord, String time, Integer historyId) {
        this.userId = userId;
        this.keyWord = keyWord;
        this.time = time;
        this.historyId = historyId;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SearchHistory{" +
                "historyId=" + historyId +
                ", userId=" + userId +
                ", keyWord='" + keyWord + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
