package com.example.masterservice.dto;

import lombok.Data;

@Data
public class PullRequestReportDTO {
    private int conversationCount;
    private int pullRequestCommitCommentCount;
    private double pullRequestDensity;
    private double pullRequestCommitDensity;
}
