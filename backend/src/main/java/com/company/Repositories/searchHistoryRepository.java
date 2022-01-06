package com.company.Repositories;

import com.company.DTOs.UserWithoutPassword;
import com.company.Entities.SearchHistory;
import com.company.utilities.Encrypter;
import io.netty.util.internal.IntegerHolder;

import java.sql.*;
import java.util.ArrayList;


public class searchHistoryRepository {
    Connection con;

    public void registerSearchHistory(SearchHistory newSearchHistory) throws SQLException {

        ArrayList<SearchHistory> searchHistories = findHistoryListByUserId(newSearchHistory.getUserId());

        if(searchHistories.size() >= 6){
            removeExceededHistoryByUserId(newSearchHistory.getUserId(), searchHistories.get(4).getHistoryId());
        }

        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }
                PreparedStatement registerSearchHistory = con.prepareStatement("INSERT INTO searchHistories (userId, keyWord, time) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                registerSearchHistory.setInt(1, newSearchHistory.getUserId());
                registerSearchHistory.setString(2, newSearchHistory.getKeyWord());
                registerSearchHistory.setString(3, newSearchHistory.getTime());
                registerSearchHistory.executeUpdate();
            }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        con.close();
    }

    public ArrayList<SearchHistory> findHistoryListByUserId(Integer userId) throws SQLException {
        ArrayList<SearchHistory> historyListOfUserDescendingByTime = new ArrayList<SearchHistory>();
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Statement searchHistoryList = con.createStatement();
            String query = "Select * from searchhistories Where userId = " + userId + " ORDER BY historyId DESC LIMIT 6;";
            ResultSet result = searchHistoryList.executeQuery(query);
            while(result.next()){
                SearchHistory searchHistory = new SearchHistory(result.getInt("userId"), result.getString("keyWord"), result.getString("time"), result.getInt("historyId"));
                historyListOfUserDescendingByTime.add(searchHistory);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        con.close();
        System.out.println(historyListOfUserDescendingByTime.size());
        return historyListOfUserDescendingByTime;
    }


    public void removeExceededHistoryByUserId(Integer userId, Integer historyId) throws SQLException {
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            PreparedStatement deleteSearchHistory = con.prepareStatement("Delete from searchhistories Where userId = ? AND historyId < ?", Statement.RETURN_GENERATED_KEYS);
            deleteSearchHistory.setInt(1, userId);
            deleteSearchHistory.setInt(2, historyId);
            deleteSearchHistory.executeUpdate();
        }

        catch (SQLException ex) {
            ex.printStackTrace();
        }

        con.close();
    }
}
