package com.company.Repositories;

import com.company.DTOs.UserWithoutPassword;
import com.company.Entities.User;
import com.company.Entities.Video;
import com.company.utilities.Encrypter;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class userRepository {
    Connection con;

    public UserWithoutPassword registerNewUser(String userName, String password, String description, String profileURL){
        UserWithoutPassword registeredUser = new UserWithoutPassword(0,userName, description, new ArrayList<Video>(), profileURL);
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            PreparedStatement tryToFindUser = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            tryToFindUser.setString(1, userName);
            ResultSet resultSetFromSearchingForUser = tryToFindUser.executeQuery();

            if(resultSetFromSearchingForUser.next()){
                con.close();
                return null; //User already exists
            }
            else{
                PreparedStatement registerUser = con.prepareStatement("INSERT INTO users (username, password, description, profileURL) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                registerUser.setString(1, userName);
                registerUser.setString(2, Encrypter.hash(password));
                registerUser.setString(3, description);
                registerUser.setString(4, profileURL);
                int resultSetFromRegisteringUser = registerUser.executeUpdate();
                try (ResultSet generatedKeys = registerUser.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        registeredUser.setUserId(generatedKeys.getInt(1));
                    }
                }
                registeredUser.setUsername(userName);
                registeredUser.setDescription(description);
                registeredUser.setProfileURL(profileURL);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return registeredUser;
    }

    public HashMap<Integer, String> getAllUsernamesById(ArrayList<Integer> userIDs){
        HashMap<Integer, String> foundUsers = new HashMap<Integer, String>();
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            StringBuilder baseQuery = new StringBuilder("SELECT * FROM users WHERE ");
            for(int i = 1; i < userIDs.size()+1; i++) {
                if(i == 1){
                    baseQuery.append("users.userId = ?");
                }
                else{
                    baseQuery.append(" OR users.userId = ?");
                }
            }

            PreparedStatement pStatement = con.prepareStatement(baseQuery.toString());
            for(int i = 1; i < userIDs.size()+1; i++) {
                pStatement.setInt(i, userIDs.get(i-1));
            }
            System.out.println("Statement was: " + pStatement);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                foundUsers.put(rs.getInt(1), rs.getString(2));
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }


        return foundUsers;
    }
    public User getUserEntityByUsername(String userName) {
        User foundUser = new User(0, "Not found", "Not found", "Not found", "Not Found");
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            PreparedStatement pStatement = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            pStatement.setString(1, userName);
            ResultSet rs = pStatement.executeQuery();


            while(rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                foundUser.setUserId(rs.getInt(1));
                foundUser.setUsername(rs.getString(2));
                foundUser.setPassword(rs.getString(3));
                foundUser.setDescription(rs.getString(4));
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }


        return foundUser;
    }

    public UserWithoutPassword getUserByUsername(String userName) {
        UserWithoutPassword foundUser = new UserWithoutPassword(0, "Not found", "Not found", new ArrayList<Video>(), "Not found");


        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            PreparedStatement pStatement = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            pStatement.setString(1, userName);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                foundUser.setUserId(rs.getInt(1));
                foundUser.setUsername(rs.getString(2));
                foundUser.setDescription(rs.getString(4));
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }


        return foundUser;
    }
    public UserWithoutPassword getUserById(Integer userId){
        // TO DO
        UserWithoutPassword foundUser = new UserWithoutPassword(0, "Not found", "Not found", new ArrayList<Video>(), "Not Found");


            try {
                try {
                    con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/kittykitty","root","root");
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                PreparedStatement pStatement = con.prepareStatement("SELECT * FROM users WHERE userId = ?");
                pStatement.setInt(1, userId);
                ResultSet rs = pStatement.executeQuery();

                while(rs.next()) {
                    // We must manually specify at which index and which datatypes each column in the result is.
                    foundUser.setUserId(rs.getInt(1));
                    foundUser.setUsername(rs.getString(2));
                    foundUser.setDescription(rs.getString(4));
                }
                con.close();
            }catch(Exception e){
                System.out.println(e);
            }


        return foundUser;
    }
}
