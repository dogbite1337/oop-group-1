package com.company.Handlers;

import com.company.Repositories.commentsRepository;
import express.Express;

public class CommentsHandler {

    private final Express app;
    private final com.company.Repositories.commentsRepository commentsRepository;

    public CommentsHandler(Express app, commentsRepository commentsRepository) {
        this.app = app;
        this.commentsRepository = commentsRepository;
        initCommentsHandler();
    }


    private void initCommentsHandler() {

        app.get("/rest/getRepliesToComment", (req, res) -> {
            res.json(commentsRepository.getRepliesForComment(Integer.parseInt(req.query("commentId"))));
        });

        app.delete("/api/removeComment", (req, res) -> {
            res.json(commentsRepository.removeComment(Integer.parseInt(req.query("commentId")), Integer.parseInt(req.query("videoId"))));
        });

        // post a comment
        app.post("/api/postComment", (req, res) -> {
            res.json(commentsRepository.postNewComment((Integer) req.body().get("relatesToVideoId"), (String) req.body().get("postedByUsername"), (String) req.body().get("content"), (Integer) req.body().get("responseToCommentId"), (Long) req.body().get("timeOfPosting")));
        });

        app.post("/api/likeComment", (req, res) -> {
            res.json(commentsRepository.likeComment(Integer.parseInt(req.query("commentId"))));
        });

        app.post("/api/dislikeComment", (req, res) -> {
            res.json(commentsRepository.dislikeComment(Integer.parseInt(req.query("commentId")), Integer.parseInt(req.query("userId"))));
        });

        app.get("/rest/getCommentsForVideoId", (req, res) -> {
            res.json(commentsRepository.getCommentsForVideo(Integer.parseInt(req.query("videoId"))));
        });
    }
}
