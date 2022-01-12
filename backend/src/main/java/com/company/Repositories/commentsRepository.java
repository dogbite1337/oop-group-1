package com.company.Repositories;

import com.company.Entities.Comment;
import com.company.utilities.Encrypter;
import java.sql.*;
import java.util.ArrayList;

public class commentsRepository {
    Connection con;

    public Comment dislikeComment(Integer commentId){
        Comment newComment = new Comment(0,0,"","",0,0,0,Long.valueOf("0"));

        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement dislikedComment = con.prepareStatement("SELECT * FROM comments WHERE commentId = ?");
            dislikedComment.setInt(1,commentId);
            ResultSet rs = dislikedComment.executeQuery();


            while(rs.next()) {
                newComment = new Comment(rs.getInt(1), rs.getInt(2), rs.getString(3) , rs.getString(4) ,rs.getInt(5),rs.getInt(6), rs.getInt(7), rs.getLong(8));
            }


            PreparedStatement dislikeComment = con.prepareStatement("UPDATE comments SET dislikes = ? WHERE commentId = ?");
            dislikeComment.setInt(1, newComment.getDislikes() + 1);
            dislikeComment.setInt(2, newComment.getCommentId());
            int resultSetFromLiking = dislikeComment.executeUpdate();



            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        newComment.setDislikes(newComment.getDislikes() + 1);
        return newComment;
    }

    public ArrayList<Comment> removeComment(Integer commentId, Integer videoId) {
        ArrayList<Comment> remainingComments = new ArrayList<Comment>();
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement removeComment = con.prepareStatement("DELETE FROM comments WHERE (commentId = ?) OR (responseToCommentId = ?)");
            removeComment.setInt(1,commentId);
            removeComment.setInt(2, commentId);
            int rs = removeComment.executeUpdate();


            PreparedStatement getRemainingComments = con.prepareStatement("SELECT * FROM comments WHERE relatesToVideoId = ?");
            getRemainingComments.setInt(1,videoId);
            ResultSet remainingCommentsInDB = getRemainingComments.executeQuery();
            while(remainingCommentsInDB.next()){
                Comment newComment = new Comment(remainingCommentsInDB.getInt(1), remainingCommentsInDB.getInt(2),
                        remainingCommentsInDB.getString(3), remainingCommentsInDB.getString(4),
                        remainingCommentsInDB.getInt(5), remainingCommentsInDB.getInt(6),
                        remainingCommentsInDB.getInt(7), remainingCommentsInDB.getLong(8));
                remainingComments.add(newComment);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return remainingComments;
    }

    public Comment likeComment(Integer commentId){
        Comment newComment = new Comment(0,0,"","",0,0,0,Long.valueOf("0"));

        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement likedComment = con.prepareStatement("SELECT * FROM comments WHERE commentId = ?");
            likedComment.setInt(1,commentId);
            ResultSet rs = likedComment.executeQuery();


            while(rs.next()) {
                newComment = new Comment(rs.getInt(1), rs.getInt(2), rs.getString(3) , rs.getString(4) ,rs.getInt(5),rs.getInt(6), rs.getInt(7), rs.getLong(8));
            }


            PreparedStatement likeComment = con.prepareStatement("UPDATE comments SET likes = ? WHERE commentId = ?");
            likeComment.setInt(1, newComment.getLikes() + 1);
            likeComment.setInt(2, newComment.getCommentId());
            int resultSetFromLiking = likeComment.executeUpdate();



            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        newComment.setLikes(newComment.getLikes() + 1);
        return newComment;
    }

    public ArrayList<Comment> getRepliesForComment(Integer commentId) {
        ArrayList<Comment> relevantReplies= new ArrayList<Comment>();
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement getAllReplies = con.prepareStatement("SELECT * FROM comments WHERE responseToCommentId = ? ORDER BY responseToCommentId ASC");
            getAllReplies.setInt(1, commentId);
            ResultSet rs = getAllReplies.executeQuery();

            while(rs.next()) {
                Comment newComment = new Comment(rs.getInt(1), rs.getInt(2), rs.getString(3) , rs.getString(4) ,rs.getInt(5),rs.getInt(6), rs.getInt(7), rs.getLong(8));
                relevantReplies.add(newComment);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return relevantReplies;

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
            PreparedStatement getAllComments = con.prepareStatement("SELECT * FROM comments WHERE relatesToVideoId = ? AND responseToCommentId = -1 ORDER BY responseToCommentId ASC");
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
