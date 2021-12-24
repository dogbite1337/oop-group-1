package com.company;

import com.company.Handlers.UserHandler;
import com.company.Repositories.userRepository;
import express.Express;

public class Application {

    Application() {
        Express app = new Express();
        app.listen(4000);
        //userRepository myUserRepository = new userRepository();
        //var foundUser = myUserRepository.getUser(1);
        new UserHandler(app, new userRepository());
    }
}
