package com.company;

import com.company.Handlers.CommentsHandler;
import com.company.Handlers.UserHandler;
import com.company.Handlers.VideoHandler;
import com.company.Repositories.commentsRepository;
import com.company.Repositories.userRepository;
import com.company.Repositories.videoRepository;
import express.Express;

public class Application {

    Application() {
        Express app = new Express();
        app.listen(4000);
        //userRepository myUserRepository = new userRepository();
        //var foundUser = myUserRepository.getUser(1);
        new UserHandler(app, new userRepository());
        new VideoHandler(app, new videoRepository());
        new CommentsHandler(app, new commentsRepository());
    }
}
