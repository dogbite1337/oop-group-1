package com.company.Repositories;

import com.company.Entities.Like;
import com.company.Entities.Video;
import java.sql.*;
import java.util.ArrayList;

public class likeRepository {
    Connection con;

    public ArrayList<Like> getLikesForVideo(Integer videoId) {
        ArrayList<Like> relevantLikes = new ArrayList<Like>();
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement checkLikesOnAVideo = con.prepareStatement("SELECT * FROM likes WHERE likedVideoId = ?");
            checkLikesOnAVideo.setInt(1, videoId);
            ResultSet allLikes = checkLikesOnAVideo.executeQuery();
            System.out.println(allLikes);
            while(allLikes.next()){
                System.out.println(allLikes.getInt(1));
                Like newLike = new Like(allLikes.getInt(1), allLikes.getInt(2), allLikes.getInt(3), allLikes.getInt(4));
                relevantLikes.add(newLike);
            }

            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return relevantLikes;
    }

    public void registerLikeOnVideo(Integer userId, Integer videoId, Integer commentId){

        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement checkVideo = con.prepareStatement("SELECT * FROM likes WHERE likedByUserId = ? AND likedVideoId = ?");
            checkVideo.setInt(1, userId);
            checkVideo.setInt(2, videoId);

            ResultSet foundVideo = checkVideo.executeQuery();
            if(!foundVideo.next()){
                PreparedStatement registerLikeOnAVideo = con.prepareStatement("INSERT IGNORE INTO likes (likedByUserId, likedVideoId, likedCommentId) VALUES (?, ?, ?)");
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

    public void registerLikeOnComment(Integer userId, Integer videoId, Integer commentId){
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement checkVideo = con.prepareStatement("SELECT * FROM likes WHERE likedByUserId = ? AND likedCommentId = ?");
            checkVideo.setInt(1, userId);
            checkVideo.setInt(2, commentId);

            ResultSet foundVideo = checkVideo.executeQuery();
            System.out.println("Tried to register a like for a comment");
            if(!foundVideo.next()){
                PreparedStatement registerLikeOnAVideo = con.prepareStatement("INSERT INTO likes (likedByUserId, likedVideoId, likedCommentId) VALUES (?, ?, ?)");
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

    public ArrayList<Like> getLikesForComment(Integer commentId) {
        ArrayList<Like> relevantLikes = new ArrayList<Like>();
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement checkLikesOnAComment = con.prepareStatement("SELECT * FROM likes WHERE likedCommentId = ?");
            checkLikesOnAComment.setInt(1, commentId);
            ResultSet allLikes = checkLikesOnAComment.executeQuery();
            while(allLikes.next()){
                Like newLike = new Like(allLikes.getInt(1), allLikes.getInt(2), allLikes.getInt(3), allLikes.getInt(4));
                System.out.println(newLike);
                relevantLikes.add(newLike);
            }

            con.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(relevantLikes);
        return relevantLikes;
    }
}
