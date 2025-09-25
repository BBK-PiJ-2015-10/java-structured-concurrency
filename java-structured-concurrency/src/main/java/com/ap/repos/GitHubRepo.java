package com.ap.repos;

import com.ap.dtos.Repository;
import com.ap.dtos.UserId;

import java.util.List;

public interface GitHubRepo {

    List<Repository> findRepositories(UserId userId);

}
