package com.company.Repositories;

import com.company.DTOs.UserWithoutPassword;

import java.sql.*;
import java.util.ArrayList;


public class subscriberRepository {
    Connection con;

    public ArrayList<UserWithoutPassword> getUsersSubscribedTo(Integer userId, Integer targetId) {
        ArrayList<UserWithoutPassword> subscribers = new ArrayList<UserWithoutPassword>();

        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            PreparedStatement pStatement = con.prepareStatement("SELECT * FROM subscribers WHERE subscribers.subscriberId = ? AND subscribedToId = ?");
            pStatement.setInt(1, userId);
            pStatement.setInt(2, targetId);
            ResultSet rs = pStatement.executeQuery();

            while (rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                UserWithoutPassword foundUser = new UserWithoutPassword(0, "", "", "", 0, 0);

                foundUser.setUserId(rs.getInt(1));
                foundUser.setUsername(rs.getString(2));
                foundUser.setDescription(rs.getString(4));
                foundUser.setProfileURL(rs.getString(5));
                foundUser.setSubscribers(rs.getInt(6));
                foundUser.setVideosPosted(rs.getInt(7));
                subscribers.add(foundUser);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return subscribers;

    }

    public Integer subscribe(Integer targetId, Integer userId) {
        Integer newSubs = 0;
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            PreparedStatement sub = con.prepareStatement("INSERT INTO subscribers (subscribedToId, subscriberId) VALUES (?, ?)");
            sub.setInt(1, targetId);
            sub.setInt(2, userId);

            sub.executeUpdate();

            PreparedStatement updateSubs = con.prepareStatement("UPDATE users SET subscribers = (subscribers + 1) WHERE userId = ?");
            updateSubs.setInt(1, targetId);
            updateSubs.executeUpdate();

            PreparedStatement getSubs = con.prepareStatement("SELECT subscribers FROM users WHERE userId = ?");
            getSubs.setInt(1, targetId);
            ResultSet newSubsCount = getSubs.executeQuery();

            while(newSubsCount.next()){
                newSubs = newSubsCount.getInt(1);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return newSubs;
    }

    public Integer unsubscribe(Integer targetId, Integer userId) {
        Integer newSubs = 0;

        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            PreparedStatement unsub = con.prepareStatement("DELETE FROM subscribers WHERE subscribedToId = ? AND subscriberId = ?");
            unsub.setInt(1, targetId);
            unsub.setInt(2, userId);

            unsub.executeUpdate();

            PreparedStatement getSubsBefore = con.prepareStatement("SELECT subscribers FROM users WHERE userId = ?");
            getSubsBefore.setInt(1, targetId);
            ResultSet newSubsCountBefore = getSubsBefore.executeQuery();


            PreparedStatement updateSubs = con.prepareStatement("UPDATE users SET subscribers = ? WHERE userId = ?");
            while(newSubsCountBefore.next()){
                updateSubs.setInt(1, (newSubsCountBefore.getInt(1) - 1));
                updateSubs.setInt(2, targetId);
            }
            updateSubs.executeUpdate();

            PreparedStatement getSubs = con.prepareStatement("SELECT subscribers FROM users WHERE userId = ?");
            getSubs.setInt(1, targetId);
            ResultSet newSubsCount = getSubs.executeQuery();

            while(newSubsCount.next()){
                newSubs = newSubsCount.getInt(1);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return newSubs;

    }

    public ArrayList<UserWithoutPassword> getSubscribersForUserWithId(Integer userId) {
        ArrayList<UserWithoutPassword> relevantUsers = new ArrayList<UserWithoutPassword>();

        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            PreparedStatement pStatement = con.prepareStatement("SELECT userId, username, description, profileURL, subscribers, videosPosted FROM subscribers, users WHERE userId = subscribedToId AND subscriberId = ?");
            pStatement.setInt(1, userId);
            ResultSet rs = pStatement.executeQuery();


            while (rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                UserWithoutPassword foundUser = new UserWithoutPassword(0, "",
                        "", "", 0, 0);
                foundUser.setUserId(rs.getInt(1));
                foundUser.setUsername(rs.getString(2));
                foundUser.setDescription(rs.getString(3));
                foundUser.setProfileURL(rs.getString(4));
                foundUser.setSubscribers(rs.getInt(5));
                foundUser.setVideosPosted(rs.getInt(6));

                relevantUsers.add(foundUser);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        return relevantUsers;

    }
}

