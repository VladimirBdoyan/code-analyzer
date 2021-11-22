package com.company.gitaccessservice.model;

import com.company.gitaccessservice.util.costant.GitHubToken;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GitOrganization {

    private GHOrganization organization;
    private String orgName;

    public GitOrganization(String orgName) {
        try {
            this.orgName = orgName;
            this.organization = GitHub.connectUsingOAuth(GitHubToken.myGitUserOuatToken).getOrganization(orgName);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public GHRepository getRepository(String repo) {
        try {
            return organization.getRepository(repo);
        } catch (IOException e) {
            System.out.println("Can not get repository: " + repo);
            throw new RuntimeException(); // TODO change by own created exception
        }

    }
    public Map<String,GHRepository> getRepositories(){
        try {
            return organization.getRepositories();
        } catch (IOException e) {
            System.out.println("Can not get repositories: " + orgName);
            throw new RuntimeException(); // TODO change by own created exception
        }
    }

    private Boolean isInDates(Long createdAt, Long since, Long till) {
        return createdAt > since && createdAt < till;
    }

}
