package com.ap.repos;

import com.ap.dtos.Repository;
import com.ap.dtos.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GitHubCachedRepo implements GitHubRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private Map<UserId, List<Repository>> cache = new HashMap<>();

    public void addToCache(UserId userId, List<Repository> repositories) {
        // try {
        //   delay(Duration.ofMillis(100));
        //} catch (InterruptedException e) {
        //  throw new RuntimeException(e);
        //}
        cache.put(userId, repositories);
    }

    @Override
    public List<Repository> findRepositories(UserId userId) {
        //logger.info("Finding cached repo for userId ", userId.userId());
        var existingRepos = cache.get(userId);
//        try {
//            delay(Duration.ofMillis(100));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        if (existingRepos == null) {
            String errorMsg = String.format("No cached repo for userId %s", userId.userId());
            logger.warn(errorMsg);
            return List.of();
            //throw new RuntimeException(String.format("Could not find on cache repo for userId %d", userId.userId()));
        } else {
            logger.info("Found cached repo for userId ", userId.userId());
            return List.of();
        }
    }

}
