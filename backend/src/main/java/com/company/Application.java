package com.company;

import com.company.Entities.SearchHistory;
import com.company.Handlers.SearchHistoryHandler;
import com.company.Handlers.UserHandler;
import com.company.Handlers.VideoHandler;
import com.company.Repositories.searchHistoryRepository;
import com.company.Repositories.userRepository;
import com.company.Repositories.videoRepository;
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
        new SearchHistoryHandler(app, new SearchHistoryLogic(new searchHistoryRepository()));
    }
}
