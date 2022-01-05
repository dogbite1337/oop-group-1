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

        // login user
        app.post("/api/postComment", (req, res) -> {
            res.json(commentsRepository.postNewComment((Integer) req.body().get("relatesToVideoId"), (String) req.body().get("postedByUsername"), (String) req.body().get("content"), (Integer) req.body().get("responseToCommentId")));
        });

    }
}
