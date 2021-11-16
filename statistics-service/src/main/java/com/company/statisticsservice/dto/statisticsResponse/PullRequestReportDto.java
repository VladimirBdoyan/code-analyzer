package com.company.statisticsservice.dto.statisticsResponse;

import javax.persistence.Column;

public class PullRequestReportDto {
    private int ConversationCount;
    private int pullRequestCommitCommentCount;
    private double pullRequestDensity;
    private double pullRequestCommitDensity;

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

    public double getPullRequestDensity() {
        return pullRequestDensity;
    }

    public void setPullRequestDensity(double pullRequestDensity) {
        this.pullRequestDensity = pullRequestDensity;
    }

    public double getPullRequestCommitDensity() {
        return pullRequestCommitDensity;
    }

    public void setPullRequestCommitDensity(double pullRequestCommitDensity) {
        this.pullRequestCommitDensity = pullRequestCommitDensity;
    }
}
