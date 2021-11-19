package com.example.masterservice.dto;


import lombok.Data;

import java.util.Date;
@Data
public class StatisticsReportDto {
    private String orgName;
    private String login;
    private String userName;
    private String repoName;
    private String sinceDate;
    private String tillDate;
    private double density;
    private Date startDate;
    private Date endDate;
    private CommitDensityDto commitDensityReport;
    private PullRequestReportDto pullRequestReport;
}
