package com.company.gitaccessservice.model;

import com.company.gitaccessservice.util.costant.GitHubToken;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;

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

    public GHOrganization getOrganization() {
        return organization;
    }

    public List<GHPullRequest> getPullRequests(String repositoryInfo) {
        try {
            GHRepository repo = organization.getRepository(repositoryInfo);
            return repo.getPullRequests(GHIssueState.ALL);
        } catch (Exception e) {
            System.out.println("Can not get pull requests for repository: " + repositoryInfo);
            throw new RuntimeException(); // TODO change by own created exception
        }
    }

    public GHRepository getRepository(String repo){
        try{
            return organization.getRepository(repo);
        } catch (IOException e) {
            System.out.println("Can not get repository: " + repo);
            throw new RuntimeException(); // TODO change by own created exception
        }
    }


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

}
