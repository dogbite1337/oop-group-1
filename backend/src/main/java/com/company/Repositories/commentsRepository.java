package com.company.Repositories;

import com.company.Entities.Comment;
import com.company.utilities.Encrypter;
import java.sql.*;
import java.util.ArrayList;

public class commentsRepository {
    Connection con;

    public Comment dislikeComment(Comment relevantComment) {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement likeComment = con.prepareStatement("UPDATE comments SET dislikes = ? WHERE commentId = ?");
            likeComment.setInt(1, relevantComment.getDislikes() + 1);
            likeComment.setInt(2, relevantComment.getCommentId());
            int rs = likeComment.executeUpdate();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        relevantComment.setDislikes(relevantComment.getDislikes() + 1);
        return relevantComment;
    }

    public Comment likeComment(Comment relevantComment){
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement likeComment = con.prepareStatement("UPDATE comments SET likes = ? WHERE commentId = ?");
            likeComment.setInt(1, relevantComment.getLikes() + 1);
            likeComment.setInt(2, relevantComment.getCommentId());
            int rs = likeComment.executeUpdate();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        relevantComment.setLikes(relevantComment.getLikes() + 1);
        return relevantComment;
    }

    public ArrayList<Comment> getCommentsForVideo(Integer videoId) {
        ArrayList<Comment> relevantComments = new ArrayList<Comment>();
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement getAllComments = con.prepareStatement("SELECT * FROM comments WHERE relatesToVideoId = ?");
            getAllComments.setInt(1, videoId);
            ResultSet rs = getAllComments.executeQuery();

            while(rs.next()) {
                Comment newComment = new Comment(rs.getInt(1), rs.getInt(2), rs.getString(3) , rs.getString(4) ,rs.getInt(5),rs.getInt(6), rs.getInt(7), rs.getLong(8));
                relevantComments.add(newComment);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return relevantComments;

    }

    public Comment postNewComment(Integer relatesToVideoId, String postedByUsername, String content, Integer responseToCommentId, Long timeOfPosting) {
        Comment newComment = new Comment(0, relatesToVideoId , postedByUsername , content ,0,0, responseToCommentId, timeOfPosting);
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement postComment = con.prepareStatement("INSERT INTO comments (relatesToVideoId, postedByUsername, content, responseToCommentId, timeOfPosting) VALUES (?, ?,?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            postComment.setInt(1, relatesToVideoId);
            postComment.setString(2, postedByUsername);
            postComment.setString(3, content);
            postComment.setInt(4, responseToCommentId);
            postComment.setLong(5, timeOfPosting);
            postComment.executeUpdate();

            try (ResultSet generatedKeys = postComment.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                   newComment.setCommentId(generatedKeys.getInt(1));
                }
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return newComment;
    }
}
