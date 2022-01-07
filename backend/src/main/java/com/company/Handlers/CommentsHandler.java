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

        // post a comment
        app.post("/api/postComment", (req, res) -> {
            res.json(commentsRepository.postNewComment((Integer) req.body().get("relatesToVideoId"), (String) req.body().get("postedByUsername"), (String) req.body().get("content"), (Integer) req.body().get("responseToCommentId"), (Integer) req.body().get("timeOfPosting")));
        });

        app.post("/api/likeComment", (req, res) -> {
           res.json(commentsRepository.likeComment(
                   new Comment(
                   (Integer) req.body().get("commentId"),
                   (Integer) req.body().get("relatesToVideoId"),
                   (String) req.body().get("postedByUsername"),
                   (String) req.body().get("content"),
                   (Integer) req.body().get("likes"),
                   (Integer) req.body().get("dislikes"),
                   (Integer) req.body().get("responseToCommentId"),
                   (Integer) req.body().get("timeOfPosting"))));
        });

        app.post("/api/dislikeComment", (req, res) -> {
            res.json(commentsRepository.dislikeComment(
                    new Comment(
                            (Integer) req.body().get("commentId"),
                            (Integer) req.body().get("relatesToVideoId"),
                            (String) req.body().get("postedByUsername"),
                            (String) req.body().get("content"),
                            (Integer) req.body().get("likes"),
                            (Integer) req.body().get("dislikes"),
                            (Integer) req.body().get("responseToCommentId"),
                            (Integer) req.body().get("timeOfPosting"))));
        });

        app.get("/rest/getCommentsForVideoId", (req, res) -> {
            res.json(commentsRepository.getCommentsForVideo(Integer.parseInt(req.query("videoId"))));
        });
    }
}
