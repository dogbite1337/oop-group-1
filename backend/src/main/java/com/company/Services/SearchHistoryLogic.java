package com.company.Services;


import com.company.Entities.SearchHistory;
import com.company.Entities.Video;
import com.company.Repositories.searchHistoryRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchHistoryLogic {

    private final searchHistoryRepository searchHistoryRepository;

    public SearchHistoryLogic(com.company.Repositories.searchHistoryRepository searchHistoryRepository) {
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public void registerSearchHistory(SearchHistory newSearchHistory) throws SQLException {
        searchHistoryRepository.registerSearchHistory(newSearchHistory);
    }

    public ArrayList<SearchHistory> getSearchHistories(Integer userId) throws SQLException {
        return searchHistoryRepository.findHistoryListByUserId(userId);
    }

    public void clearHistories(Integer userId) throws SQLException {
        searchHistoryRepository.clearHistories(userId);
    }

    public ArrayList<String> getTrendingSearch() throws SQLException {
        return searchHistoryRepository.getTrendingSearch();
    }

    public ArrayList<Video> getMatchedVideoList(String keyword) throws SQLException {
        return searchHistoryRepository.getMatchedVideoList(keyword);
    }
}
