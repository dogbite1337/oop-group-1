package com.company.Repositories;

import com.company.DTOs.UserWithoutPassword;
import com.company.DTOs.VideoWithUsername;
import com.company.Entities.User;
import com.company.Entities.Video;
import com.company.Services.DTOConverter;
import com.company.utilities.Encrypter;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class videoRepository {
    Connection con;

    public ArrayList<Video> getVideosByUsername(String username) {
        ArrayList<Video> allVideosByUser = new ArrayList<Video>();

        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String baseQuery = ("SELECT * FROM videos WHERE postedByUsername LIKE '%$replaceThis%'").replace("$replaceThis", username);
            PreparedStatement pStatement = con.prepareStatement(baseQuery);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                Video newVideo = new Video(0,0,"Not found", "Not found", "Not found", 0, "");
                newVideo.setVideoId(rs.getInt(1));
                newVideo.setUserId(rs.getInt(2));
                newVideo.setLink(rs.getString(3));
                newVideo.setTitle(rs.getString(4));
                newVideo.setDescription(rs.getString(5));
                newVideo.setViews(rs.getInt(6));
                newVideo.setPostedByUsername(rs.getString(7));
                allVideosByUser.add(newVideo);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return allVideosByUser;
    }

    public ArrayList<Video> getVideosByTitle(String title) {
        ArrayList<Video> videosToReturn = new ArrayList<Video>();

        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String baseQuery = ("SELECT * FROM videos WHERE title LIKE '%$replaceThis%'").replace("$replaceThis", title);
            PreparedStatement pStatement = con.prepareStatement(baseQuery);
            //pStatement.setString(1, title);
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                Video newVideo = new Video(0,0,"Not found", "Not found", "Not found", 0, "");
                newVideo.setVideoId(rs.getInt(1));
                newVideo.setUserId(rs.getInt(2));
                newVideo.setLink(rs.getString(3));
                newVideo.setTitle(rs.getString(4));
                newVideo.setDescription(rs.getString(5));
                newVideo.setViews(rs.getInt(6));
                newVideo.setPostedByUsername(rs.getString(7));
                videosToReturn.add(newVideo);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return videosToReturn;
    }

    public ArrayList<Video> getAllVideos(){
        ArrayList<Video> videosToReturn= new ArrayList<Video>();

        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            PreparedStatement pStatement = con.prepareStatement("SELECT * FROM videos");
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                Video newVideo = new Video(0,0,"Not found", "Not found", "Not found", 0, "");
                newVideo.setVideoId(rs.getInt(1));
                newVideo.setUserId(rs.getInt(2));
                newVideo.setLink(rs.getString(3));
                newVideo.setTitle(rs.getString(4));
                newVideo.setDescription(rs.getString(5));
                newVideo.setViews(rs.getInt(6));
                newVideo.setPostedByUsername(rs.getString(7));
                videosToReturn.add(newVideo);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return videosToReturn;
    }

}
