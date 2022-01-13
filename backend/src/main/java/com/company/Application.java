package com.company;

import com.company.Handlers.*;
import com.company.Entities.SearchHistory;
import com.company.Repositories.*;
import com.company.Services.SearchHistoryLogic;
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
        new SearchHistoryHandler(app, new SearchHistoryLogic(new searchHistoryRepository()));
        new LikeHandler(app, new likeRepository());
        new DislikeHandler(app, new dislikeRepository());
    }
}
