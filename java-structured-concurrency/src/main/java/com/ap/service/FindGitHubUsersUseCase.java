package com.ap.service;

import com.ap.dtos.GitHubUser;
import com.ap.dtos.UserId;

import java.util.List;

public interface FindGitHubUsersUseCase {

    List<GitHubUser> findGitHubUsers(List<UserId> userIds);

}
