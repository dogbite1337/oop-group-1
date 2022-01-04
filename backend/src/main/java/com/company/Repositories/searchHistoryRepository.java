package com.company.Repositories;

import com.company.DTOs.UserWithoutPassword;
import com.company.utilities.Encrypter;

import java.sql.*;
import java.util.ArrayList;

public class searchHistoryRepository {
    Connection con;

    public void registerSearchHistory(Integer userId, String keyword) throws SQLException {
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }
                PreparedStatement registerSearchHistory = con.prepareStatement("INSERT INTO searchHistories (userId, keyword) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
                registerSearchHistory.setInt(1, userId);
                registerSearchHistory.setString(2, keyword);
                registerSearchHistory.executeUpdate();

            }
        catch (SQLException ex) {
            ex.printStackTrace();
        }

        con.close();
    }
}
