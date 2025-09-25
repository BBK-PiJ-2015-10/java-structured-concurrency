package com.ap.repos;

import com.ap.dtos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.time.Duration;
import java.util.List;

public class GitHubRepoImpl implements GitHubRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private void delay(Duration duration) throws InterruptedException {
        Thread.sleep(duration);
    }

    @Override
    public List<Repository> findRepositories(UserId userId) {
        logger.info("Finding repos for userId {}", userId.userId());
        try {
            delay(Duration.ofMillis(500));
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
        }
        var repositories = List.of(
                new Repository(
                        "raise4s", Visibility.PUBLIC, URI.create("https://github.com/rcardin/raise4s")),
                new Repository(
                        "sus4s", Visibility.PUBLIC, URI.create("https://github.com/rcardin/sus4s")));
        logger.info("Found repos for userId {}",userId.userId());
        return repositories;

    }
}
