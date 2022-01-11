package com.company.Repositories;

import com.company.Entities.Video;
import java.sql.*;
import java.util.ArrayList;

public class videoRepository {
    Connection con;

    public Integer likeVideo(Integer videoId, Integer likes) {
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String baseQuery = ("UPDATE videos SET likes = ? WHERE videoId = ?");
            PreparedStatement likeVideo = con.prepareStatement(baseQuery);
            likeVideo.setInt(1, (likes + 1));
            likeVideo.setInt(2, videoId);

            int rs = likeVideo.executeUpdate();

            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return (likes + 1);
    }

    public Integer dislikeVideo(Integer videoId, Integer dislikes) {
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String baseQuery = ("UPDATE videos SET dislikes = ? WHERE videoId = ?");
            PreparedStatement likeVideo = con.prepareStatement(baseQuery);
            likeVideo.setInt(1, (dislikes + 1));
            likeVideo.setInt(2, videoId);

            int rs = likeVideo.executeUpdate();

            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return (dislikes + 1);
    }

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
                Video newVideo = new Video(0,0, 0,"Not found", "Not found", "Not found", 0, "", "0", "0", "0");
                newVideo.setVideoId(rs.getInt(1));
                newVideo.setUserId(rs.getInt(2));
                newVideo.setUploadDate(rs.getTimestamp(3).getTime());
                newVideo.setVideoURL(rs.getString(4));
                newVideo.setTitle(rs.getString(5));
                newVideo.setDescription(rs.getString(6));
                newVideo.setViews(rs.getInt(7));
                newVideo.setPostedByUsername(rs.getString(8));
                newVideo.setLikes(String.valueOf(rs.getInt(9)));
                newVideo.setDislikes(String.valueOf(rs.getInt(10)));
                newVideo.setStars(String.valueOf(rs.getInt(11)));
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
                Video newVideo = new Video(0,0, 0,"Not found", "Not found", "Not found", 0, "", "0", "0", "0");
                newVideo.setVideoId(rs.getInt(1));
                newVideo.setUserId(rs.getInt(2));
                newVideo.setUploadDate(rs.getTimestamp(3).getTime());
                newVideo.setVideoURL(rs.getString(4));
                newVideo.setTitle(rs.getString(5));
                newVideo.setDescription(rs.getString(6));
                newVideo.setViews(rs.getInt(7));
                newVideo.setPostedByUsername(rs.getString(8));
                newVideo.setLikes(String.valueOf(rs.getInt(9)));
                newVideo.setDislikes(String.valueOf(rs.getInt(10)));
                newVideo.setStars(String.valueOf(rs.getInt(11)));
                videosToReturn.add(newVideo);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return videosToReturn;
    }

    public ArrayList<Video> getVideosForCurrentPage(Integer currentPage){
        ArrayList<Video> videosToReturn = new ArrayList<Video>();
        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            PreparedStatement pStatement = con.prepareStatement("SELECT * FROM videos LIMIT 8 OFFSET ?");
            pStatement.setInt(1, (currentPage == 1 ? 0 : currentPage * 8));
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()) {
                // We must manually specify at which index and which datatypes each column in the result is.
                Video newVideo = new Video(0,0, 0,"Not found", "Not found", "Not found", 0, "", "0", "0", "0");
                newVideo.setVideoId(rs.getInt(1));
                newVideo.setUserId(rs.getInt(2));
                newVideo.setUploadDate(rs.getTimestamp(3).getTime());
                newVideo.setVideoURL(rs.getString(4));
                newVideo.setTitle(rs.getString(5));
                newVideo.setDescription(rs.getString(6));
                newVideo.setViews(rs.getInt(7));
                newVideo.setPostedByUsername(rs.getString(8));
                newVideo.setLikes(String.valueOf(rs.getInt(9)));
                newVideo.setDislikes(String.valueOf(rs.getInt(10)));
                newVideo.setStars(String.valueOf(rs.getInt(11)));
                videosToReturn.add(newVideo);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return videosToReturn;
    }

    public ArrayList<Video> getAllVideos(){
        ArrayList<Video> videosToReturn = new ArrayList<Video>();

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
                Video newVideo = new Video(0,0, 0,"Not found", "Not found", "Not found", 0, "", "0","0", "0");
                newVideo.setVideoId(rs.getInt(1));
                newVideo.setUserId(rs.getInt(2));
                newVideo.setUploadDate(rs.getTimestamp(3).getTime());
                newVideo.setVideoURL(rs.getString(4));
                newVideo.setTitle(rs.getString(5));
                newVideo.setDescription(rs.getString(6));
                newVideo.setViews(rs.getInt(7));
                newVideo.setPostedByUsername(rs.getString(8));
                newVideo.setLikes(String.valueOf(rs.getInt(9)));
                newVideo.setDislikes(String.valueOf(rs.getInt(10)));
                newVideo.setStars(String.valueOf(rs.getInt(11)));
                videosToReturn.add(newVideo);
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return videosToReturn;
    }

    public Video getVideoById(String videoId)
    {
        Video relevantVideo = new Video(0, 0, 0, "", "", "", 0, "", "0", "0", "0");

        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            PreparedStatement pStatement = con.prepareStatement("SELECT * FROM videos WHERE videos.videoId = ?");
            pStatement.setInt(1, Integer.parseInt(videoId));
            ResultSet rs = pStatement.executeQuery();

            while(rs.next()){
                relevantVideo.setVideoId(rs.getInt(1));
                relevantVideo.setUserId(rs.getInt(2));
                relevantVideo.setUploadDate(rs.getTimestamp(3).getTime());
                relevantVideo.setVideoURL(rs.getString(4));
                relevantVideo.setTitle(rs.getString(5));
                relevantVideo.setDescription(rs.getString(6));
                relevantVideo.setViews(rs.getInt(7));
                relevantVideo.setPostedByUsername(rs.getString(8));
                relevantVideo.setLikes(String.valueOf(rs.getInt(9)));
                relevantVideo.setDislikes(String.valueOf(rs.getInt(10)));
                relevantVideo.setStars(String.valueOf(rs.getInt(11)));
            }

            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return relevantVideo;
    }

    public Video uploadNewVideo(String userIdOfUpload, String uploadDate, String videoURL, String title, String description, String views, String postedByUsername, String likes, String dislikes, String stars)
    {
        Video uploadedVideo = new Video(0, 0, 0, "", "", "", 0, "", "0", "0", "0");

        uploadedVideo.setUserId(Integer.parseInt(userIdOfUpload));
        uploadedVideo.setUploadDate(Long.parseLong(uploadDate));
        uploadedVideo.setVideoURL(videoURL);
        uploadedVideo.setTitle(title);
        uploadedVideo.setDescription(description);
        uploadedVideo.setViews(Integer.parseInt(views));
        uploadedVideo.setPostedByUsername(postedByUsername);
        uploadedVideo.setLikes(likes);
        uploadedVideo.setDislikes(dislikes);
        uploadedVideo.setStars(stars);

        try {
            try {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/kittykitty","root","root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            PreparedStatement pStatement = con.prepareStatement("INSERT INTO videos (userId, uploadDate, videoURL, title, description, views, postedByUsername, likes, dislikes, stars) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pStatement.setInt(1, uploadedVideo.getUserId());
            pStatement.setTimestamp(2, new Timestamp(uploadedVideo.getUploadDate()));
            pStatement.setString(3, uploadedVideo.getVideoURL());
            pStatement.setString(4, uploadedVideo.getTitle());
            pStatement.setString(5, uploadedVideo.getDescription());
            pStatement.setInt(6, uploadedVideo.getViews());
            pStatement.setString(7, uploadedVideo.getPostedByUsername());
            pStatement.setString(8, uploadedVideo.getLikes());
            pStatement.setString(9, uploadedVideo.getDislikes());
            pStatement.setString(10, uploadedVideo.getStars());
            int newVideoUpload = pStatement.executeUpdate();

            try (ResultSet generatedKeys = pStatement.getGeneratedKeys()){
                if (generatedKeys.next()){
                    uploadedVideo.setVideoId(generatedKeys.getInt(1));
                }
            }
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }

        return uploadedVideo;
    }

    public void incrementViewCount(String videoId) throws SQLException {
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/kittykitty","root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String baseQuery = ("UPDATE videos SET views = views + 1 WHERE videoId = $replaceThis").replace("$replaceThis", videoId);
        PreparedStatement pStatement = con.prepareStatement(baseQuery);
        pStatement.executeUpdate();
        con.close();
    }
}
