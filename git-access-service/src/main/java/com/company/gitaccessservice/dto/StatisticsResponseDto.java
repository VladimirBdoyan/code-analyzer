package com.company.gitaccessservice.dto;

import com.company.gitaccessservice.dto.commit.GitAccessCommitDetailsDto;
import com.company.gitaccessservice.dto.pullRequest.GitAccessPullRequestDetailsDto;

public class StatisticsResponseDto {
    private String userName;
    private String login;
    private String organization;
    private String repoName;
    private GitAccessCommitDetailsDto commitDetailsDto;
    private GitAccessPullRequestDetailsDto pullRequestDetailsDto;
    private Long since;
    private Long till;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public void setCommitFrequencyDTO(GitAccessCommitDetailsDto gitAccessCommitDetailsDto) {
        this.commitDetailsDto = gitAccessCommitDetailsDto;
    }

    public void setGitUserPullRequestsDetailsDTO(GitAccessPullRequestDetailsDto pullRequestsDetailsDTO) {
        this.pullRequestDetailsDto = pullRequestsDetailsDTO;
    }

    public String getUserName() {
        return userName;
    }

    public String getLogin() {
        return login;
    }

    public String getRepoName() {
        return repoName;
    }

    public GitAccessCommitDetailsDto getCommitDetailsDto() {
        return commitDetailsDto;
    }

    public GitAccessPullRequestDetailsDto getPullRequestDetailsDto() {
        return pullRequestDetailsDto;
    }

    public Long getSince() {
        return since;
    }

    public void setSince(Long since) {
        this.since = since;
    }

    public Long getTill() {
        return till;
    }

    public void setTill(Long till) {
        this.till = till;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
}
