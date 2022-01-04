package com.company.Handlers;

import com.company.Repositories.searchHistoryRepository;
import com.company.Services.SearchHistoryLogic;
import express.Express;

import java.sql.SQLException;
import java.util.ArrayList;

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
                searchHistoryLogic.registerSearchHistory((Integer) req.body().get("userId"), (String)req.body().get("keyword"));
                res.json("true");
            } catch (SQLException e) {
                e.printStackTrace();
                res.json("false");
            }
        });
    }
}
