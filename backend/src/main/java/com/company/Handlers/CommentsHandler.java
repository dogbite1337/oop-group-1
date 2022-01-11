package com.company.Handlers;

import com.company.Entities.Comment;
import com.company.Repositories.commentsRepository;
import com.company.utilities.Validator;
import express.Express;

public class CommentsHandler {

    private final Express app;
    private final com.company.Repositories.commentsRepository commentsRepository;
    private final Validator loginValidator;

    public CommentsHandler(Express app, commentsRepository commentsRepository){
        this.app = app;
        this.commentsRepository = commentsRepository;
        this.loginValidator = new Validator();
        initCommentsHandler();
    }


    private void initCommentsHandler() {

        app.get("/rest/getRepliesToComment", (req, res) -> {
            res.json(commentsRepository.getRepliesForComment(Integer.parseInt(req.query("commentId"))));
        });

        // post a comment
        app.post("/api/postComment", (req, res) -> {
            res.json(commentsRepository.postNewComment((Integer) req.body().get("relatesToVideoId"), (String) req.body().get("postedByUsername"), (String) req.body().get("content"), (Integer) req.body().get("responseToCommentId"), (Long) req.body().get("timeOfPosting")));
        });

        app.post("/api/likeComment", (req, res) -> {
           res.json(commentsRepository.likeComment((Integer) req.body().get("commentId")));
        });

        app.post("/api/dislikeComment", (req, res) -> {
            res.json(commentsRepository.dislikeComment((Integer) req.body().get("commentId")));
        });

        app.get("/rest/getCommentsForVideoId", (req, res) -> {
            res.json(commentsRepository.getCommentsForVideo(Integer.parseInt(req.query("videoId"))));
        });
    }
}