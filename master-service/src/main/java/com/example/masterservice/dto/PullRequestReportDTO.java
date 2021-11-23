package com.example.masterservice.dto;

import lombok.Data;

@Data
public class PullRequestReportDTO {
    private Integer conversationCount;
    private Integer pullRequestCommitCommentCount;
    private Double pullRequestDensity;
    private Double pullRequestCommitDensity;
}
