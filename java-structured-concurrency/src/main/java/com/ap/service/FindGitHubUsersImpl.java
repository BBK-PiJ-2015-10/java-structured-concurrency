package com.ap.service;

import com.ap.dtos.GitHubUser;
import com.ap.dtos.UserId;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Joiner;

public class FindGitHubUsersImpl implements FindGitHubUsersUseCase {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final FindGitHubUserUseCase findGitHubUserUseCase;

    public FindGitHubUsersImpl(FindGitHubUserUseCase findGitHubUserUseCase) {
        this.findGitHubUserUseCase = findGitHubUserUseCase;
    }

    @Override
    public List<GitHubUser> findGitHubUsers(List<UserId> userIds) {
        try (var scope = StructuredTaskScope.open(Joiner.<GitHubUser>allSuccessfulOrThrow())) {
            userIds.forEach(userId -> scope.fork(() -> findGitHubUserUseCase.findGitHubUser(userId)));
            var result = scope.join().map(StructuredTaskScope.Subtask::get).toList();
            return result;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
