package com.ap;

import com.ap.dtos.UserId;
import com.ap.repos.GitHubRepo;
import com.ap.repos.GitHubRepoImpl;
import com.ap.repos.UserRepo;
import com.ap.repos.UserRepoImpl;
import com.ap.service.FindGitHubUserImpl;
import com.ap.service.FindGitHubUserUseCase;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// source:
// https://rockthejvm.com/articles/structured-concurrency-jdk-25
public class Main {


    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");


        GitHubRepo gitHubRepo = new GitHubRepoImpl();
        UserRepo userRepo = new UserRepoImpl();

        FindGitHubUserUseCase useCase = new FindGitHubUserImpl(gitHubRepo, userRepo);

        UserId userId1 = new UserId(1L);

        useCase.findGitHubUser(userId1);

        //service.findRepositories(userId1);

    }
}