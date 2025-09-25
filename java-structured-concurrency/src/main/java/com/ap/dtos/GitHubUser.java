package com.ap.dtos;


import java.util.List;

public record GitHubUser(User user, List<Repository> repositories) {
}
