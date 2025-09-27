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

    private GitHubRepoRemoteImpl remoteRepo;

    private GitHubCachedRepo cachedRepo;

    public GitHubRepoImpl(GitHubCachedRepo cachedRepo, GitHubRepoRemoteImpl remoteRepo) {
        this.remoteRepo = remoteRepo;
        this.cachedRepo = cachedRepo;
    }

    // StructuredTaskScope.open(Joiner.<List<Repository>>anySuccessfulResultOrThrow()))
    @Override
    public List<Repository> findRepositories(UserId userId) throws InterruptedException {
        //logger.info("Finding repo for userId ", userId.userId());
        // the joiner discards the subtask that fails if any following subtask succeeds
        // StructuredTaskScope.open(Joiner.<List<Repository>>anySuccessfulResultOrThrow())
        //  try (var scope = StructuredTaskScope.open(Joiner.<List<Repository>>anySuccessfulResultOrThrow())) {
        try (var scope = StructuredTaskScope.open(Joiner.<List<Repository>>allSuccessfulOrThrow())) {
            scope.fork(() -> cachedRepo.findRepositories(userId));
            scope.fork(() -> {
                List<Repository> remoteRepos = remoteRepo.findRepositories(userId);
                logger.info("Adding to cache repo for userId ", userId.userId());
                cachedRepo.addToCache(userId, remoteRepos);

            });

            var repositories = scope.join().map(StructuredTaskScope.Subtask::get).flatMap(List::stream).collect(Collectors.toList());
            logger.info("Found repo for userId {} with size {}", userId, repositories.size());
            return repositories;
        }
    }
}
