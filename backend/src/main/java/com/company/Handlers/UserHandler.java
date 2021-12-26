package com.company.Handlers;

import com.company.Repositories.userRepository;
import com.company.utilities.Validator;
import express.Express;


import java.util.Map;

public class UserHandler {

    private final Express app;
    private final userRepository userRepository;
    private final Validator loginValidator;

    public UserHandler(Express app, userRepository userRepository){
        this.app = app;
        this.userRepository = userRepository;
        this.loginValidator = new Validator();
        initUserHandler();
    }


    private void initUserHandler() {

        // login user
        app.post("/api/login", (req, res) -> {
            res.json(loginValidator.loginValidation((String) req.body().get("providedUserName"), (String) req.body().get("providedPassword"), userRepository));
        });

        // register user
        app.post("/api/register", (req, res) -> {
            res.json(userRepository.registerNewUser((String) req.body().get("providedUserName"), (String) req.body().get("providedPassword"), (String) req.body().get("providedDescription"), (String) req.body().get("providedProfileURL")));
        });

        app.get("/api/logout", (req, res) -> {
            // remove user from session
            req.session("current-user", null);
        });

    }
}

