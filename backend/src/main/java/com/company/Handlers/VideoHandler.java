package com.company.Handlers;

import com.company.Repositories.videoRepository;
import express.Express;

import java.sql.SQLException;

public class VideoHandler {
    private final Express app;
    private final videoRepository videoRepository;

    public VideoHandler(Express app, videoRepository videoRepository) {
        this.app = app;
        this.videoRepository = videoRepository;
        initVideoHandler();
    }

    private void initVideoHandler() {
        // get All videos
        app.get("/rest/getAllVideos", (req, res) -> {
            res.json(videoRepository.getAllVideos());
        });

        app.get("/rest/getVideosForCurrentPage", (req, res) -> {
            res.json(videoRepository.getVideosForCurrentPage(Integer.parseInt(req.query("currentPage"))));
        });

        app.get("/rest/getAllVideosByTitle", (req, res) -> {
            res.json(videoRepository.getVideosByTitle(req.query("providedTitle")));
        });

        // get All videos by the user with the following username
        app.get("/rest/getAllVideosByUsername", (req, res) -> {
            res.json(videoRepository.getVideosByUsername(req.query("providedUsername")));
        });

        app.get("/rest/getVideoById", (req, res) -> {
            res.json(videoRepository.getVideoById(req.query("videoId")));
        });

        app.post("/api/likeVideo", (req, res) -> {
            res.json(videoRepository.likeVideo(
                    Integer.parseInt(req.body().get("userId").toString()),
                    Integer.parseInt(req.body().get("videoId").toString()),
                    Integer.parseInt(req.body().get("likes").toString())
            ));
        });

        app.delete("/api/deleteVideo", (req, res) -> {
           videoRepository.deleteVideo(Integer.parseInt(req.query("videoId")));
        });

        app.post("/api/updateVideo", (req, res) -> {
            videoRepository.updateVideo(
                    Integer.parseInt(req.body().get("videoId").toString()),
                    req.body().get("videoURL").toString(),
                    req.body().get("title").toString(),
                    req.body().get("description").toString());
            res.json("Updated video");
        });

        app.post("/api/dislikeVideo", (req, res) -> {
            res.json(videoRepository.dislikeVideo(
                    Integer.parseInt(req.body().get("userId").toString()),
                    Integer.parseInt(req.body().get("videoId").toString()),
                    Integer.parseInt(req.body().get("dislikes").toString())
            ));
        });

        app.post("/api/uploadVideo", (req, res) -> {
            res.json(videoRepository.uploadNewVideo(
                    req.body().get("uploaderId").toString(),
                    req.body().get("uploadDate").toString(),
                    req.body().get("videoURL").toString(),
                    req.body().get("title").toString(),
                    req.body().get("description").toString(),
                    req.body().get("views").toString(),
                    req.body().get("postedByUsername").toString(),
                    req.body().get("likes").toString(),
                    req.body().get("dislikes").toString(),
                    req.body().get("stars").toString())
            );
        });

        app.put("/api/incrementViewCount", (req, res) -> {
            try {
                videoRepository.incrementViewCount(req.body().get("videoId").toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        app.get("/rest/getEightNewVideos",(req,res) ->{
            try {
//                System.out.println("runned");
                System.out.println(Integer.parseInt(req.query("lengthOfCurrentVideoList")));
                res.json(videoRepository.getNextEightVideos(Integer.parseInt(req.query("lengthOfCurrentVideoList"))));
            } catch (SQLException e) {
//                e.printStackTrace();
                System.out.println("error");
            }
        });
    }
}


