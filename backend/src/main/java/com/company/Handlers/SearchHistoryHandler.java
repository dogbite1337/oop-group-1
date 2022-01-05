package com.company.Handlers;

import com.company.Entities.SearchHistory;
import com.company.Repositories.searchHistoryRepository;
import com.company.Services.SearchHistoryLogic;
import express.Express;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SearchHistoryHandler {
    private final Express app;
    private final SearchHistoryLogic searchHistoryLogic;

    public SearchHistoryHandler(Express app, SearchHistoryLogic searchHistoryLogic){
        this.app = app;
        this.searchHistoryLogic = searchHistoryLogic;
        initSearchHistoryRepository();
    }

    private void initSearchHistoryRepository() {
        app.post("/api/registerHistory", (req, res) -> {
            try {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SearchHistory newSearch = new SearchHistory((Integer) req.body().get("userId"), (String)req.body().get("keyWord"), formatter.format(date));
                searchHistoryLogic.registerSearchHistory(newSearch);
                res.json("true");
            } catch (SQLException e) {
                e.printStackTrace();
                res.json("false");
            }
        });
    }
}
