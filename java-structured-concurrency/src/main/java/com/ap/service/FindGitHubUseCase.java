package com.ap.service;

import com.ap.dtos.GitHubUser;
import com.ap.dtos.UserId;

public interface FindGitHubUseCase {

    GitHubUser findGitHubUser(UserId userId);
}
