package com.company.gitaccessservice.service;

import com.company.gitaccessservice.model.GitUser;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;

import java.util.List;
import java.util.Map;

public class GitAccessImpl implements GitAccess{


    private String userName = "VladimirBdoyan"; // sa petqa ta ogtagorcoxy
    private GitUser gitUser = GitUser.setGitUser(userName);

    private Map<String, List<GHCommit>> reposCommits;

    private Map<String, GHRepository> userRepos = gitUser.setRepos();


    // Map by key RepositoryName value all Commits

    @Override
    public Map<String, List<GHCommit>> getReposCommits(RepositoryInfo reposInfo) {
        for (GHRepository repo : userRepos.values()) {
            reposCommits.put(reposInfo.getRepoName(), reposInfo.getCommits());
        }
        return reposCommits;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
