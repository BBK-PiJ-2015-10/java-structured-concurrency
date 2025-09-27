package com.ap.repos;

import com.ap.dtos.Repository;
import com.ap.dtos.UserId;

import java.time.Duration;
import java.util.List;

public interface GitHubRepo {

    List<Repository> findRepositories(UserId userId) throws InterruptedException;

    default void delay(Duration duration) throws InterruptedException {
        Thread.sleep(duration);
    }

}
