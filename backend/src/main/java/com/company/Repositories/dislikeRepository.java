package com.company.Repositories;

import com.company.Entities.Dislike;
import java.sql.*;
import java.util.ArrayList;

public class dislikeRepository {
    Connection con;

    public ArrayList<Dislike> getDislikesForVideo(Integer videoId) {
        ArrayList<Dislike> relevantDislikes = new ArrayList<Dislike>();
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement registerDislikeOnAVideo = con.prepareStatement("SELECT * FROM dislikes WHERE dislikedVideoId = ?");
            registerDislikeOnAVideo.setInt(1, videoId);
            ResultSet allDislikes = registerDislikeOnAVideo.executeQuery();
            while(allDislikes.next()){
                System.out.println(allDislikes.getInt(1));
                Dislike newDislike = new Dislike(allDislikes.getInt(1), allDislikes.getInt(2), allDislikes.getInt(3), allDislikes.getInt(4));
                relevantDislikes.add(newDislike);
            }

            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return relevantDislikes;
    }

    public void registerDislikeOnVideo(Integer userId, Integer videoId, Integer commentId){

        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement checkVideo = con.prepareStatement("SELECT * FROM dislikes WHERE dislikedByUserId = ? AND dislikedVideoId = ?");
            checkVideo.setInt(1, userId);
            checkVideo.setInt(2, videoId);

            ResultSet foundVideo = checkVideo.executeQuery();
            if(!foundVideo.next()){
                PreparedStatement registerLikeOnAVideo = con.prepareStatement("INSERT IGNORE INTO dislikes (dislikedByUserId, dislikedVideoId, dislikedCommentId) VALUES (?, ?, ?)");
                registerLikeOnAVideo.setInt(1, userId);
                registerLikeOnAVideo.setInt(2, videoId);
                registerLikeOnAVideo.setInt(3, commentId);
                registerLikeOnAVideo.executeUpdate();
            }

            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Dislike> getDislikesForComment(Integer commentId){
        ArrayList<Dislike> relevantDislikes = new ArrayList<Dislike>();
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement registerDislikeOnAVideo = con.prepareStatement("SELECT * FROM dislikes WHERE dislikedCommentId = ?");
            registerDislikeOnAVideo.setInt(1, commentId);
            ResultSet allDislikes = registerDislikeOnAVideo.executeQuery();
            while(allDislikes.next()){
                Dislike newDislike = new Dislike(allDislikes.getInt(1), allDislikes.getInt(2), allDislikes.getInt(3), allDislikes.getInt(4));
                System.out.println(newDislike);
                relevantDislikes.add(newDislike);
            }

            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return relevantDislikes;
    }
}

