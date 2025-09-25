package com.ap.service;

import com.ap.dtos.GitHubUser;
import com.ap.dtos.Repository;
import com.ap.dtos.User;
import com.ap.dtos.UserId;
import com.ap.repos.GitHubRepo;
import com.ap.repos.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.temporal.ValueRange;
import java.util.List;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class FindGitHubUseCaseImpl implements FindGitHubUseCase {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final GitHubRepo gitHubRepo;

    private final UserRepo userRepo;

    public FindGitHubUseCaseImpl(GitHubRepo gitHubRepo, UserRepo userRepo) {
        this.gitHubRepo = gitHubRepo;
        this.userRepo = userRepo;
    }

    @Override
    public GitHubUser findGitHubUser(UserId userId) {
        try (var scope = StructuredTaskScope.open()) {
            Subtask<User> user = scope.fork(() -> userRepo.findUserByIdPort(userId));
            Subtask<List<Repository>> repositories = scope.fork(() -> gitHubRepo.findRepositories(userId));
            // The above two are parallel, only blocked by below
            scope.join();
            return new GitHubUser(user.get(), repositories.get());
        } catch (InterruptedException e) {
            logger.error("Something went off {}", e);
            throw new RuntimeException(e);
        }
    }
}
