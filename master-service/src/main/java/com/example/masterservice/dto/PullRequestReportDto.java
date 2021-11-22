package com.example.masterservice.dto;

public class PullRequestReportDto {
    private int ConversationCount;
    private int pullRequestCommitCommentCount;
    private Double pullRequestDensity;
    private Double pullRequestCommitDensity;

    public int getConversationCount() {
        return ConversationCount;
    }

    public void setConversationCount(int conversationCount) {
        ConversationCount = conversationCount;
    }

    public int getPullRequestCommitCommentCount() {
        return pullRequestCommitCommentCount;
    }

    public void setPullRequestCommitCommentCount(int pullRequestCommitCommentCount) {
        this.pullRequestCommitCommentCount = pullRequestCommitCommentCount;
    }

    public Double getPullRequestDensity() {
        return pullRequestDensity;
    }

    public void setPullRequestDensity(Double pullRequestDensity) {
        this.pullRequestDensity = pullRequestDensity;
    }

    public Double getPullRequestCommitDensity() {
        return pullRequestCommitDensity;
    }

    public void setPullRequestCommitDensity(Double pullRequestCommitDensity) {
        this.pullRequestCommitDensity = pullRequestCommitDensity;
    }
}
