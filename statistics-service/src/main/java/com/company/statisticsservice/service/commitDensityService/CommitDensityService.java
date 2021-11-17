package com.company.statisticsservice.service.commitDensityService;

import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.dto.statisticsResponse.StatisticsReportDto;
import com.company.statisticsservice.entity.CommitDensity;
import com.company.statisticsservice.entity.GitOrganization;
import com.company.statisticsservice.entity.GitRepository;
import com.company.statisticsservice.entity.GitUser;

import java.util.Date;
import java.util.List;

public interface CommitDensityService {

    List<StatisticsReportDto> getCommitDensityReports(Long userId);
    StatisticsReportDto getReportByParams(Long userId, Long repoId, Date since, Date till);
    CommitDensity getReportByParamsOrg(GitOrganization organization, GitUser user, Date since, Date till);
    StatisticsReportDto getReportById(Long id);
    CommitDensity creatReport(GitOrganization organization,GitUser gitUser, GitRepository repo,
                              GitAccessResponseDto dto);
}
