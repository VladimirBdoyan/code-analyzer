package com.company.gitaccessservice.model;

import com.company.gitaccessservice.exception.GitUserNotFoundException;
import com.company.gitaccessservice.util.costant.GitHubToken;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GitUser extends GHUser {

    private String login;
    private GHUser user;
    private GHRepository repo;
    private Map<String, GHRepository> repos = new HashMap<>();

   public GitUser (String login){
       try {
           this.user = GitHub.connectUsingOAuth(GitHubToken.myGitUserOuatToken).getUser(login);
       } catch (IOException e) {
           throw new GitUserNotFoundException(login);
       }
   }

    private GitUser() {
    }

    public static GitUser setGitUser(String login) {
        GitUser gitUser = new GitUser();
        try {
            GHUser gitHub = GitHub.connectUsingOAuth(GitHubToken.myGitUserOuatToken).getUser(login);
            gitUser.setUser(gitHub);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gitUser;
    }

    public void setUser(GHUser user) {
        this.user = user;
    }
    public GHUser getUser() {
        return user;
    }


    public Map<String, GHRepository> setRepos() {
        return getUserRepositories();
    }

    public List<String> setReposName() {
        return new ArrayList<>(setRepos().keySet());
    }

    @Override
    public GHRepository getRepository(String name)  {

       try {
           repo = user.getRepository(name);
       }catch (IOException e){
           e.printStackTrace();
       }
       return repo;
    }

    private Map<String, GHRepository> getUserRepositories() {
        try {
            repos = user.getRepositories();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repos;
    }

    public List<GHPullRequest> getPullRequests(GHRepository repo, Long since, Long till) {
        List<GHPullRequest> pullRequests;
        try {
            pullRequests = repo.getPullRequests(GHIssueState.ALL)
                    .stream().filter(pullRequest -> {
                        try {
                            return isInDates(pullRequest.getCreatedAt().getTime(), since, till);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return false;
                    })
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("Can not get pull requests for repository: " + repo.getName());
            throw new RuntimeException(); // TODO change by own created exception
        }
        return pullRequests;
    }

    private Boolean isInDates(Long createdAt, Long since, Long till) {
        return createdAt > since && createdAt < till;
    }

    public long getId() {
        return user.getId();
    }

    @Override
    public String toString() {
        return "GitUser{" +
                "userName='" + login + '\'' +
                ", user=" + user +
                ", repos=" + repos +
                '}';
    }
}
