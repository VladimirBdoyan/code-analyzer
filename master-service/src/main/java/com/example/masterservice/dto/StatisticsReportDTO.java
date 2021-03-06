package com.example.masterservice.dto;


import lombok.Data;

import java.util.Date;

@Data
public class StatisticsReportDTO {
    private String orgName;
    private String login;
    private String userName;
    private String repoName;
    private Double commitDensity;
    private PullRequestReportDTO pullRequestReport;
    private Date startDate;
    private Date endDate;
}
