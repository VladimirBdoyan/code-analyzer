package com.company.statisticsservice.dto.gitAccessResponse;

public class GitAccessPullRequestDetailsDto {
    private String repoName;
    private String organization;
    private String userName;
    private Integer pullRequestCountByGitUser;
    private Integer pullRequestCountByEveryOne;
    private Integer pullRequestConversationCount; // only where there are issue messages
    private Integer pullRequestCommitCountByUser;
    private Integer pullRequestCommitCountByEveryOne;
    private Integer pullRequestCommitCommentCount;

    public GitAccessPullRequestDetailsDto() {
    }

    public String getRepoName() {
        return repoName;
    }

    public String getUserName() {
        return userName;
    }

    public String getOrganization() {
        return organization;
    }

    public Integer getPullRequestCountByGitUser() {
        return pullRequestCountByGitUser;
    }

    public Integer getPullRequestCountByEveryOne() {
        return pullRequestCountByEveryOne;
    }

    public Integer getPullRequestConversationCount() {
        return pullRequestConversationCount;
    }

    public Integer getPullRequestCommitCountByUser() {
        return pullRequestCommitCountByUser;
    }

    public Integer getPullRequestCommitCountByEveryOne() {
        return pullRequestCommitCountByEveryOne;
    }

    public Integer getPullRequestCommitCommentCount() {
        return pullRequestCommitCommentCount;
    }
}
