package com.company.statisticsservice.mapper;

import com.company.statisticsservice.dto.gitAccessResponse.GitAccessPullRequestDetailsDto;
import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.dto.statisticsResponse.PullRequestReportDto;
import com.company.statisticsservice.entity.PullRequestReport;
import com.company.statisticsservice.util.Calculate;

import java.util.Date;

public class PullRequestReportMapper {

    public static PullRequestReport mapToEntity(GitAccessResponseDto dto) {

        GitAccessPullRequestDetailsDto pullRequestDetailsDto = dto.getPullRequestDetailsDto();
        PullRequestReport pullRequestsReport = new PullRequestReport();
        pullRequestsReport.setConversationCount(pullRequestDetailsDto.getPullRequestConversationCount());
        pullRequestsReport.setPullRequestCommitCommentCount(pullRequestDetailsDto.getPullRequestCommitCommentCount());
        if (pullRequestDetailsDto.getPullRequestCountByGitUser() == 0) {
            pullRequestsReport.setPullRequestDensity(0.0);
        } else {
            pullRequestsReport.setPullRequestDensity(
                    Calculate.density(pullRequestDetailsDto.getPullRequestCountByGitUser()
                            , pullRequestDetailsDto.getPullRequestCountByEveryOne()));
        }
        if (pullRequestDetailsDto.getPullRequestCommitCountByUser() == 0) {
            pullRequestsReport.setPullRequestCommitDensity(0.0);
        } else {
            pullRequestsReport.setPullRequestCommitDensity(
                    Calculate.density(pullRequestDetailsDto.getPullRequestCommitCountByUser()
                            , pullRequestDetailsDto.getPullRequestCommitCountByEveryOne()));
        }
        pullRequestsReport.setStartDate(new Date(dto.getSince()));
        pullRequestsReport.setEndDate(new Date(dto.getTill()));

        return pullRequestsReport;
    }

    public static PullRequestReportDto mapToDto(PullRequestReport pullRequestReport){
        PullRequestReportDto reportDto = new PullRequestReportDto();
        reportDto.setPullRequestCommitCommentCount(pullRequestReport.getPullRequestCommitCommentCount());
        reportDto.setPullRequestDensity(pullRequestReport.getPullRequestDensity());
        reportDto.setPullRequestCommitDensity(pullRequestReport.getPullRequestCommitDensity());
        reportDto.setPullRequestCommitDensity(pullRequestReport.getPullRequestCommitDensity());
        return reportDto;
    }
}
