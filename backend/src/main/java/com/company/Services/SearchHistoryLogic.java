package com.company.Services;


import com.company.Entities.SearchHistory;
import com.company.Repositories.searchHistoryRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchHistoryLogic {

    private final searchHistoryRepository searchHistoryRepository;

    public SearchHistoryLogic(com.company.Repositories.searchHistoryRepository searchHistoryRepository){
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public void registerSearchHistory(Integer userId, String keyword) throws SQLException {
        searchHistoryRepository.registerSearchHistory(userId, keyword);
    }
}
