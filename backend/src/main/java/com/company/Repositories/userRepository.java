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

    public UserWithoutPassword registerNewUser(String userName, String password, String description, String profileURL, Integer subscribers, Integer videosPosted) {
        UserWithoutPassword registeredUser = new UserWithoutPassword(0, "", "", "", 0, 0);
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            PreparedStatement tryToFindUser = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            tryToFindUser.setString(1, userName);
            ResultSet resultSetFromSearchingForUser = tryToFindUser.executeQuery();

            if (resultSetFromSearchingForUser.next()) {
                con.close();
                return null; //User already exists
            } else {
                PreparedStatement registerUser = con.prepareStatement("INSERT INTO users (username, password, description, profileURL, subscribers, videosPosted) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                registerUser.setString(1, userName);
                registerUser.setString(2, Encrypter.hash(password));
                registerUser.setString(3, description);
                registerUser.setString(4, profileURL);
                registerUser.setInt(5, subscribers);
                registerUser.setInt(6, videosPosted);
                int resultSetFromRegisteringUser = registerUser.executeUpdate();
                try (ResultSet generatedKeys = registerUser.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        registeredUser.setUserId(generatedKeys.getInt(1));
                    }
                }
                registeredUser.setUsername(userName);
                registeredUser.setDescription(description);
                registeredUser.setProfileURL(profileURL);
                registeredUser.setSubscribers(0);
                registeredUser.setVideosPosted(0);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return registeredUser;
    }

    public void updateUserInfo(Integer userId, String newUsername, String newPassword, String newDescription, String profileURL) {
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(newPassword.length() > 0){
                PreparedStatement updateVideo = con.prepareStatement("UPDATE users SET username = ?, password = ?, description = ?, profileURL = ? WHERE userId = ?");
                updateVideo.setString(1, newUsername);
                updateVideo.setString(2, Encrypter.hash(newPassword));
                updateVideo.setString(3, newDescription);
                updateVideo.setString(4, profileURL);
                updateVideo.setInt(5, userId);
                updateVideo.executeUpdate();
            }
            else {
                PreparedStatement updateVideo = con.prepareStatement("UPDATE users SET username = ?, description = ?, profileURL = ? WHERE userId = ?");
                updateVideo.setString(1, newUsername);
                updateVideo.setString(2, newDescription);
                updateVideo.setString(3, profileURL);
                updateVideo.setInt(4, userId);
                updateVideo.executeUpdate();
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateUserInfoWithNewPW(Integer userId, String newUsername, String newPassword, String newDescription, String profileURL) {
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            newPassword = Encrypter.hash(newPassword);
            PreparedStatement updateVideo = con.prepareStatement("UPDATE users SET username = ?, password = ?, description = ?, profileURL = ? WHERE userId = ?");
            updateVideo.setString(1, newUsername);
            updateVideo.setString(2, newPassword);
            updateVideo.setString(3, newDescription);
            updateVideo.setString(4, profileURL);
            updateVideo.setInt(5, userId);
            updateVideo.executeUpdate();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public HashMap<Integer, String> getAllUsernamesById(ArrayList<Integer> userIDs) {
        HashMap<Integer, String> foundUsers = new HashMap<Integer, String>();
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            StringBuilder baseQuery = new StringBuilder("SELECT * FROM users WHERE ");
            for (int i = 1; i < userIDs.size() + 1; i++) {
                if (i == 1) {
                    baseQuery.append("users.userId = ?");
                } else {
                    baseQuery.append(" OR users.userId = ?");
                }
            }

            PreparedStatement pStatement = con.prepareStatement(baseQuery.toString());
            for (int i = 1; i < userIDs.size() + 1; i++) {
                pStatement.setInt(i, userIDs.get(i - 1));
            }

            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                foundUsers.put(rs.getInt(1), rs.getString(2));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return foundUsers;
    }

    public User getUserEntityByUsername(String userName) {
        User foundUser = new User(0, "Not found", "Not found", "Not found", "Not Found", 0, 0);
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            PreparedStatement pStatement = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            pStatement.setString(1, userName);
            ResultSet rs = pStatement.executeQuery();


            while (rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                foundUser.setUserId(rs.getInt(1));
                foundUser.setUsername(rs.getString(2));
                foundUser.setPassword(rs.getString(3));
                foundUser.setDescription(rs.getString(4));
                foundUser.setProfileURL(rs.getString(5));
                foundUser.setSubscribers(rs.getInt(6));
                foundUser.setVideosPosted(rs.getInt(7));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return foundUser;
    }

    public UserWithoutPassword getUserByUsername(String userName) {
        UserWithoutPassword foundUser = new UserWithoutPassword(0, "", "", "", 0, 0);


        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            PreparedStatement pStatement = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            pStatement.setString(1, userName);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                foundUser.setUserId(rs.getInt(1));
                foundUser.setUsername(rs.getString(2));
                foundUser.setDescription(rs.getString(4));
                foundUser.setProfileURL(rs.getString(5));
                foundUser.setSubscribers(rs.getInt(6));
                foundUser.setVideosPosted(rs.getInt(7));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return foundUser;
    }

    public UserWithoutPassword getUserById(Integer userId) {
        UserWithoutPassword foundUser = new UserWithoutPassword(0, "", "", "", 0, 0);

        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            PreparedStatement pStatement = con.prepareStatement("SELECT * FROM users WHERE userId = ?");
            pStatement.setInt(1, userId);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                foundUser.setUserId(rs.getInt(1));
                foundUser.setUsername(rs.getString(2));
                foundUser.setDescription(rs.getString(4));
                foundUser.setProfileURL(rs.getString(5));
                foundUser.setSubscribers(rs.getInt(6));
                foundUser.setVideosPosted(rs.getInt(7));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return foundUser;
    }

    public ArrayList<User> getMatchedUserList(String keyword) {
        ArrayList<User> matchedUserList = new ArrayList<>();
        ArrayList<Video> videoList;

        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            PreparedStatement pStatement = con.prepareStatement("SELECT * FROM users WHERE LOWER(username) LIKE LOWER(?)");
            pStatement.setString(1, keyword);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                videoList = matchedUserVideoList(rs.getInt(1));
                matchedUserList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), videoList));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return matchedUserList;
    }

    public ArrayList<Video> matchedUserVideoList(Integer userId) {

        ArrayList<Video> videoListOfUser = new ArrayList<>();
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            PreparedStatement pStatement2 = con.prepareStatement("SELECT * FROM videos WHERE userId = ?");
            pStatement2.setInt(1, userId);
            ResultSet videosRS = pStatement2.executeQuery();

            while (videosRS.next()) {
                videoListOfUser.add(new Video(videosRS.getInt("videoId"),
                        videosRS.getInt("userId"),
                        videosRS.getTimestamp("uploadDate").getTime(),
                        videosRS.getString("videoUrl"),
                        videosRS.getString("title"),
                        videosRS.getString("description"),
                        videosRS.getInt("views"),
                        videosRS.getString("postedByUsername"),
                        String.valueOf(videosRS.getInt("likes")),
                        String.valueOf(videosRS.getInt("dislikes")),
                        String.valueOf(videosRS.getInt("stars"))));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return videoListOfUser;
    }
}
