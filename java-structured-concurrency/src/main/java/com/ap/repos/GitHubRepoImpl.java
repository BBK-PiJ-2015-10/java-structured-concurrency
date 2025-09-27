package com.ap.repos;

import com.ap.dtos.Repository;
import com.ap.dtos.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Joiner;
import java.util.stream.Collectors;

public class GitHubRepoImpl implements GitHubRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private GitHubRepoRemoteImpl remote;

    private GitHubCachedRepo cache;

    public GitHubRepoImpl(GitHubCachedRepo cachedRepo, GitHubRepoRemoteImpl remoteRepo) {
        this.remote = remoteRepo;
        this.cache = cachedRepo;
    }

    // StructuredTaskScope.open(Joiner.<List<Repository>>anySuccessfulResultOrThrow()))
    @Override
    public List<Repository> findRepositories(UserId userId) throws InterruptedException {
        //logger.info("Finding repo for userId ", userId.userId());
        // the joiner discards the subtask that fails if any following subtask succeeds
        // StructuredTaskScope.open(Joiner.<List<Repository>>anySuccessfulResultOrThrow())
        //  try (var scope = StructuredTaskScope.open(Joiner.<List<Repository>>anySuccessfulResultOrThrow())) {
        try (var scope = StructuredTaskScope.open(Joiner.<List<Repository>>anySuccessfulResultOrThrow())) {
            scope.fork(() -> cache.findRepositories(userId));
            scope.fork(() -> {
                final List<Repository> remoteRepos = remote.findRepositories(userId);
                logger.info("Adding to cache repo for userId ", userId.userId());
                cache.addToCache(userId, remoteRepos);
            });
            logger.info("Found repo for userId {} with size {}", userId);
            return scope.join();
        }
    }
}
