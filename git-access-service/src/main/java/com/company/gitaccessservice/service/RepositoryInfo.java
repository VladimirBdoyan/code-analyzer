package com.company.gitaccessservice.service;

import org.kohsuke.github.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepositoryInfo extends GHRepository {

    private GHRepository repository;
    private List<GHCommit> commits;
    private List<Contributor> contributors;
    private List<String> branchNames;

    //      Constructor
    private RepositoryInfo() {
    }

    public GHRepository getRepository() {
        return repository;
    }

    public void setRepository(GHRepository repository) {
        this.repository = repository;
    }

    //      Define GitHub Repository by url
    public static RepositoryInfo setRepo(String url) {
        RepositoryInfo repositoryInfo = new RepositoryInfo();
        {
            try {
                GitHub gitHub = GitHub.connectAnonymously();
                repositoryInfo.setRepository(gitHub.getRepository(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return repositoryInfo;
    }

    public List<GHCommit> getCommits() {
        try {
            commits = repository.listCommits().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commits;
    }

    public List<Date> getCommitDatesFromRepo() {
        List<Date> commitDate = new ArrayList<>();
        PagedIterable<GHCommit> commits;
        List<GHCommit> allCommits;
        {
            try {
                commits = repository.listCommits();
                allCommits = commits.toList();
                for (GHCommit commit : allCommits) {
                    commitDate.add(commit.getCommitDate());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return commitDate;
    }

    public String getRepoName() {
        return repository.getName();
    }

    //      It's repo Author and that users who set of email addresses is found in the commit history of a github repo
    public List<Contributor> getContributors() {
        try {
            contributors = repository.listContributors().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contributors;
    }


    //    Download Repository
    public void downloadRepo() {

    }

    List<GHRepositoryStatistics.CommitActivity> commitActivities = new ArrayList<>();
}
