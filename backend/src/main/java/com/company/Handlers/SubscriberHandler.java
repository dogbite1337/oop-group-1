package com.company.Handlers;

import com.company.Repositories.subscriberRepository;
import express.Express;

public class SubscriberHandler {

    private final Express app;
    private final com.company.Repositories.subscriberRepository subscriberRepository;

    public SubscriberHandler(Express app, subscriberRepository subscriberRepository) {
        this.app = app;
        this.subscriberRepository = subscriberRepository;
        initSubscriberHandler();
    }


    private void initSubscriberHandler() {
        app.get("/rest/getSubscribersForId", (req, res) -> {
            res.json(subscriberRepository.getSubscribersForUserWithId(Integer.parseInt(req.query("userId"))));
        });

        app.get("/rest/getEveryoneSubscribedToId", (req, res) -> {
            res.json(subscriberRepository.getUsersSubscribedTo(Integer.parseInt(req.query("targetId")),
                    Integer.parseInt(req.query("userId"))
            ));
        });

        app.delete("/api/unsubscribe", (req, res) -> {
            res.json(subscriberRepository.unsubscribe(Integer.parseInt(req.query("targetId")), Integer.parseInt(req.query("userId"))));
        });

        app.post("/api/subscribe", (req, res) -> {
            res.json(subscriberRepository.subscribe(Integer.parseInt(req.query("targetId")), Integer.parseInt(req.query("userId"))));
        });
    }
}


