package com.ap.repos;

import com.ap.dtos.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.time.Duration;
import java.util.List;

public class GitHubRepoRemoteImpl implements GitHubRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public List<Repository> findRepositories(UserId userId) {
        logger.info("Finding remote repos for userId {}", userId.userId());
        try {
            delay(Duration.ofMillis(500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (userId.userId()==2){
            throw new RuntimeException("This has gone crazy");
        }
        var repositories = List.of(
                new Repository(
                        "raise4s", Visibility.PUBLIC, URI.create("https://github.com/rcardin/raise4s")),
                new Repository(
                        "sus4s", Visibility.PUBLIC, URI.create("https://github.com/rcardin/sus4s")));
        logger.info("Found remote repos for userId {}", userId.userId());
        return repositories;
    }
}
