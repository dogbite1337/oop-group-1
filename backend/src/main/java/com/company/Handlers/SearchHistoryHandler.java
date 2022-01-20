package com.company.Handlers;

import com.company.Entities.SearchHistory;
import com.company.Services.SearchHistoryLogic;
import express.Express;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SearchHistoryHandler {
    private final Express app;
    private final SearchHistoryLogic searchHistoryLogic;

    public SearchHistoryHandler(Express app, SearchHistoryLogic searchHistoryLogic) {
        this.app = app;
        this.searchHistoryLogic = searchHistoryLogic;
        initSearchHistoryHandler();
    }

    private void initSearchHistoryHandler() {
        app.post("/api/registerHistory", (req, res) -> {
            try {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SearchHistory newSearch = new SearchHistory((Integer) req.body().get("userId"), (String) req.body().get("keyWord"), formatter.format(date), null);
                searchHistoryLogic.registerSearchHistory(newSearch);
                res.json("true");
            } catch (SQLException e) {
                e.printStackTrace();
                res.json("false");
            }
        });

        app.get("rest/getSearchHistories/:id", (req, res) -> {
            Integer userId = Integer.valueOf(req.params("id"));
            try {
                ArrayList<SearchHistory> searchHistory = searchHistoryLogic.getSearchHistories(userId);
                res.json(searchHistory);
            } catch (SQLException e) {
                e.printStackTrace();
                res.json("false");
            }
        });

        app.delete("api/clearHistories/:id", (req, res) -> {
            Integer userId = Integer.valueOf(req.params("id"));
            try {
                searchHistoryLogic.clearHistories(userId);
                res.json("true");
            } catch (SQLException e) {
                e.printStackTrace();
                res.json("false");
            }
        });

        app.get("api/getTrendingSearch", (req, res) -> {
            try {
                res.json(searchHistoryLogic.getTrendingSearch());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        app.get("rest/getMatchedVideoList/:keyword", (req, res) -> {
            String keyword = req.params("keyword");
            try {
                res.json(searchHistoryLogic.getMatchedVideoList(keyword));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
