package com.company.statisticsservice.service.gitPullRequest;

import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.entity.GitOrganization;
import com.company.statisticsservice.entity.GitRepository;
import com.company.statisticsservice.entity.GitUser;
import com.company.statisticsservice.entity.PullRequestReport;

import java.util.Date;

public interface PullRequestReportService {

    PullRequestReport getPullRequestReportsByUser(GitUser user);

    PullRequestReport getPullRequestReportsByParamsOrg(GitOrganization organization, GitUser user, Date since, Date till);

    PullRequestReport createReport(GitOrganization organization,GitUser gitUser, GitRepository repo, GitAccessResponseDto dto);

}
