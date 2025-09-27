package com.ap.repos;

import com.ap.dtos.Repository;
import com.ap.dtos.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Joiner;

public class GitHubRepoImpl implements GitHubRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private GitHubRepoRemoteImpl remote;

    private GitHubCachedRepo cache;

    public GitHubRepoImpl(GitHubCachedRepo cachedRepo, GitHubRepoRemoteImpl remoteRepo) {
        this.remote = remoteRepo;
        this.cache = cachedRepo;
    }

    @Override
    public List<Repository> findRepositories(UserId userId)
            throws InterruptedException, StructuredTaskScope.FailedException {
        try (var scope =
                     StructuredTaskScope.open(Joiner.<List<Repository>>anySuccessfulResultOrThrow())) {
            scope.fork(() -> cache.findRepositories(userId));
            scope.fork(() -> remote.findRepositories(userId));
//                    () -> {
//                        final List<Repository> repositories = remote.findRepositories(userId);
//                        logger.info("Received {} remote repositories ",repositories.size());
//                        cache.addToCache(userId, repositories);
//                        return repositories;
//                    });
            return scope.join();
        }
    }
}
