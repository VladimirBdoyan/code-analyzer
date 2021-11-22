package com.company.statisticsservice.mapper;


import com.company.statisticsservice.dto.statisticsResponse.StatisticsReportDto;
import com.company.statisticsservice.entity.CommitDensity;
import com.company.statisticsservice.entity.PullRequestReport;

public class StatisticsReportDtoMapper {

    public static StatisticsReportDto mapToDto(CommitDensity commitDensity, PullRequestReport pullRequestReport){
        StatisticsReportDto reportDto = new StatisticsReportDto();
        try {
            reportDto.setOrgName(commitDensity.getOrganization().getName());
            reportDto.setRepoName(commitDensity.getRepoName().getName());
        } catch (NullPointerException e){
            reportDto.setOrgName("");
            reportDto.setRepoName("");
        }
        reportDto.setLogin(commitDensity.getUserName().getLogin());
        reportDto.setUserName(commitDensity.getUserName().getName());

        reportDto.setStartDate(commitDensity.getStartDate());
        reportDto.setEndDate(commitDensity.getEndDate());
        reportDto.setCommitDensityReportDto(CommitDensityMapper.mapToDtoDensity(commitDensity));
        reportDto.setPullRequestReportDto(PullRequestReportMapper.mapToDto(pullRequestReport));
        return reportDto;
    }
}
