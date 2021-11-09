package com.company.gitaccessservice.model;

import com.company.gitaccessservice.util.costant.GitHubToken;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitUser extends GHUser {

    private String userName;
    private GHUser user;
    private Map<String, GHRepository> repos = new HashMap<>();

    public GHUser getUser() {
        return user;
    }

    private GitUser() {
    }

    public static GitUser setGitUser(String name) {
        GitUser gitUser = new GitUser();
        try {
            GitHub gitHub = GitHub.connectUsingOAuth(GitHubToken.myGitUserOuatToken);
            gitUser.setUser(gitHub.getUser(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gitUser;
    }

    public void setUser(GHUser user) {
        this.user = user;
    }


    public Map<String, GHRepository> setRepos() {
        return getUserRepositories();
    }

    public List<String> setReposName() {
        return new ArrayList<>(setRepos().keySet());
    }


    // Connection to GitHub concrete user page
    private GHUser connectToGitUser() {
        GitHub connect;
        try {
            connect = GitHub.connectAnonymously();
            user = connect.getUser(userName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    private Map<String, GHRepository> getUserRepositories() {
        try {
            repos = connectToGitUser().getRepositories();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repos;
    }

    public long getId() {
        return user.getId();
    }

    @Override
    public String toString() {
        return "GitUser{" +
                "userName='" + userName + '\'' +
                ", user=" + user +
                ", repos=" + repos +
                '}';
    }
}
