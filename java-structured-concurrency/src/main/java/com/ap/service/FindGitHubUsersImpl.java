package com.ap.service;


import com.ap.dtos.GitHubUser;
import com.ap.dtos.UserId;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class FindGitHubUsersImpl implements FindGitHubUsersUseCase {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final FindGitHubUserUseCase findGitHubUserUseCase;

    public FindGitHubUsersImpl(FindGitHubUserUseCase findGitHubUserUseCase) {
        this.findGitHubUserUseCase = findGitHubUserUseCase;
    }


    @Override
    public List<GitHubUser> findGitHubUsers(List<UserId> userIds) {
        return List.of();
    }
}
