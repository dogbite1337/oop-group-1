package com.company.Handlers;

import com.company.Repositories.videoRepository;
import express.Express;

public class VideoHandler {
    private final Express app;
    private final videoRepository videoRepository;

    public VideoHandler(Express app, videoRepository videoRepository){
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
            res.json(videoRepository.getVideosByTitle((String) req.query("providedTitle")));
        });

        // get All videos by the user with the following username
        app.get("/rest/getAllVideosByUsername", (req, res) -> {
            res.json(videoRepository.getVideosByUsername((String) req.query("providedUsername")));
        });

        app.post("/api/uploadVideo", (req, res) -> {
            res.json(videoRepository.uploadNewVideo(
                    req.body().get("uploaderId").toString(),
                    req.body().get("videoURL").toString(),
                    req.body().get("title").toString(),
                    req.body().get("description").toString(),
                    req.body().get("views").toString(),
                    req.body().get("postedByUsername").toString()));
        });

    }
}


