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
        System.out.println("Initialized video handler");
    }

    private void initVideoHandler() {
        // get All videos
        app.get("/rest/getAllVideos", (req, res) -> {
            res.json(videoRepository.getAllVideos());
        });

        app.get("/rest/getAllVideosByTitle", (req, res) -> {
            res.json(videoRepository.getVideosByTitle((String) req.query("providedTitle")));
        });

        // get All videos by the user with the following username
        app.get("/rest/getAllVideosByUsername", (req, res) -> {
            res.json(videoRepository.getVideosByUsername((String) req.query("providedUsername")));
        });

    }
}


