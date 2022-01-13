package com.company.Handlers;

import com.company.Repositories.dislikeRepository;
import express.Express;

public class DislikeHandler {
    private final Express app;
    private final com.company.Repositories.dislikeRepository dislikeRepository;

    public DislikeHandler(Express app, dislikeRepository dislikeRepository){
        this.app = app;
        this.dislikeRepository = dislikeRepository;
        initDislikeHandler();
    }

    private void initDislikeHandler() {
        app.post("/api/registerDislikeOnVideo", (req, res) -> {
            dislikeRepository.registerDislikeOnVideo(Integer.parseInt(req.body().get("userId").toString()), Integer.parseInt(req.body().get("videoId").toString()), Integer.parseInt(req.body().get("commentId").toString()));
            res.json(0);
        });

        app.get("/rest/getDislikesForVideo", (req, res) -> {
            res.json(dislikeRepository.getDislikesForVideo(Integer.parseInt(req.query("videoId"))));
        });

        app.get("/rest/getDislikesForComment", (req, res) -> {
           res.json(dislikeRepository.getDislikesForComment(Integer.parseInt(req.query("commentId"))));
        });

    }

}
