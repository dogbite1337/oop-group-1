package com.company.Entities;

public class SearchHistory {
    private Integer historyId, userId;
    private String keyword;

    public SearchHistory() {
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "SearchHistory{" +
                "historyId=" + historyId +
                ", userId=" + userId +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
