package com.company.Handlers;

import com.company.Repositories.likeRepository;
import express.Express;

public class LikeHandler {
    private final Express app;
    private final com.company.Repositories.likeRepository likeRepository;

    public LikeHandler(Express app, likeRepository likeRepository){
        this.app = app;
        this.likeRepository = likeRepository;
        initLikeHandler();
    }

    private void initLikeHandler() {
        app.post("/api/registerLikeOnComment", (req, res) -> {
            likeRepository.registerLikeOnComment(Integer.parseInt(req.query("userId")), Integer.parseInt(req.query("videoId")), Integer.parseInt(req.query("commentId")));
            res.json(0);
        });

        app.get("/rest/getLikesForComment", (req, res) -> {
            res.json(likeRepository.getLikesForComment(Integer.parseInt(req.query("commentId"))));
        });

        app.post("/api/registerLikeOnVideo", (req, res) -> {
            likeRepository.registerLikeOnVideo(Integer.parseInt(req.body().get("userId").toString()), Integer.parseInt(req.body().get("videoId").toString()), Integer.parseInt(req.body().get("commentId").toString()));
            res.json(0);
        });

        app.get("/rest/getLikesForVideo", (req, res) -> {
            res.json(likeRepository.getLikesForVideo(Integer.parseInt(req.query("videoId"))));
        });

    }

}


