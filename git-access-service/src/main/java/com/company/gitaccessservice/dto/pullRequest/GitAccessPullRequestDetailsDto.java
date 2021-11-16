package com.company.gitaccessservice.dto.pullRequest;

public class GitAccessPullRequestDetailsDto {
    private String repoName;
    private String organization;
    private String userName;
    private Integer PullRequestCountByGitUser;
    private Integer PullRequestCountByEveryOne;
    private Integer pullRequestConversationCount; // only where there are issue messages
    private Integer pullRequestCommitCountByUser;
    private Integer pullRequestCommitCountByEveryOne;
    private Integer pullRequestCommitCommentCount;

    public GitAccessPullRequestDetailsDto(String organization, String repoName, String userName, Integer pullRequestCountByGitUser
            , Integer pullRequestCountByEveryOne, Integer pullRequestConversationCount
            , Integer pullRequestCommitCountByUser, Integer pullRequestCommitCountByEveryOne
            , Integer pullRequestCommitCommentCount) {
        this.organization = organization;
        this.repoName = repoName;
        this.userName = userName;
        this.PullRequestCountByGitUser = pullRequestCountByGitUser;
        this.PullRequestCountByEveryOne = pullRequestCountByEveryOne;
        this.pullRequestConversationCount = pullRequestConversationCount;
        this.pullRequestCommitCountByUser = pullRequestCommitCountByUser;
        this.pullRequestCommitCountByEveryOne = pullRequestCommitCountByEveryOne;
        this.pullRequestCommitCommentCount = pullRequestCommitCommentCount;
    }
    public GitAccessPullRequestDetailsDto(String organization, String userName, Integer pullRequestCountByGitUser
            , Integer pullRequestCountByEveryOne, Integer pullRequestConversationCount
            , Integer pullRequestCommitCountByUser, Integer pullRequestCommitCountByEveryOne
            , Integer pullRequestCommitCommentCount) {
        this.organization = organization;
        this.userName = userName;
        this.PullRequestCountByGitUser = pullRequestCountByGitUser;
        this.PullRequestCountByEveryOne = pullRequestCountByEveryOne;
        this.pullRequestConversationCount = pullRequestConversationCount;
        this.pullRequestCommitCountByUser = pullRequestCommitCountByUser;
        this.pullRequestCommitCountByEveryOne = pullRequestCommitCountByEveryOne;
        this.pullRequestCommitCommentCount = pullRequestCommitCommentCount;
    }

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
        return PullRequestCountByGitUser;
    }

    public Integer getPullRequestCountByEveryOne() {
        return PullRequestCountByEveryOne;
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
