package com.company.Repositories;

import com.company.Entities.Comment;
import com.company.utilities.Encrypter;
import java.sql.*;

public class commentsRepository {
    Connection con;

    public Comment postNewComment(Integer relatesToVideoId, String postedByUsername, String content, Integer responseToCommentId) {
        Comment newComment = new Comment(0, relatesToVideoId , postedByUsername , content ,0,0, responseToCommentId);
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement postComment = con.prepareStatement("INSERT INTO comments (relatesToVideoId, postedByUsername, content, responseToCommentId) VALUES (?, ?,?, ?)", Statement.RETURN_GENERATED_KEYS);
            postComment.setInt(1, relatesToVideoId);
            postComment.setString(2, postedByUsername);
            postComment.setString(3, content);
            postComment.setInt(4, responseToCommentId);
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
