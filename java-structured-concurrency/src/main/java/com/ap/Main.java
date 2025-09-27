package com.ap;

import com.ap.dtos.UserId;
import com.ap.repos.*;
import com.ap.service.FindGitHubUserImpl;
import com.ap.service.FindGitHubUserUseCase;
import com.ap.service.FindGitHubUsersImpl;
import com.ap.service.FindGitHubUsersUseCase;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// source:
// https://rockthejvm.com/articles/structured-concurrency-jdk-25
public class Main {


    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");


        GitHubCachedRepo cachedRepo = new GitHubCachedRepo();
        GitHubRepoRemoteImpl remoteRepo = new GitHubRepoRemoteImpl();
        GitHubRepo gitHubRepo = new GitHubRepoImpl(cachedRepo,remoteRepo);

        UserRepo userRepo = new UserRepoImpl();

        FindGitHubUserUseCase useCase = new FindGitHubUserImpl(gitHubRepo, userRepo);
        FindGitHubUsersUseCase useCases = new FindGitHubUsersImpl(useCase);

        UserId userId1 = new UserId(1L);
        UserId userId2 = new UserId(2L);

        var result = useCases.findGitHubUsers(List.of(userId1, userId2));

        //var other = result.stream().map(d -> d.repositories().size()).reduce((a, b) -> a +b).get();

        //System.out.println(other);

        result.forEach(a -> System.out.println(a.user().userId().userId()));

        //service.findRepositories(userId1);

    }
}