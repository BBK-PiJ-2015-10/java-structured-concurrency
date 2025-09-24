package com.ap.services;

import com.ap.dtos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.time.Duration;
import java.util.List;

import static com.ap.dtos.Visibility.PUBLIC;

public class GitHubRepository implements GitHubRepo, UserRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private void delay(Duration duration) throws InterruptedException {
        Thread.sleep(duration);
    }

    @Override
    public List<Repository> findRepositories(UserId userId) {
        logger.info("Finding repo for userId {}", userId.userId());
        var repositories = List.of(new Repository("alexis", PUBLIC, URI.create("dd")));
        return repositories;
    }

    @Override
    public User findUserByIdPort(UserId userId) {
        logger.info("Finding user with id {}", userId);
        User user = new User(new Username("alex"), userId, new Email("alexis.gmail.com"));
        return user;
    }
}
