package com.company.statisticsservice.controler;


import com.company.statisticsservice.service.commitDensityService.CommitDensityServiceImpl;
import com.company.statisticsservice.service.gitOrganizationService.OrganizationServiceImpl;
import com.company.statisticsservice.service.gitPullRequest.PullRequestReportServiceImpl;
import com.company.statisticsservice.service.gitRepository.GitRepositoryServiceImpl;
import com.company.statisticsservice.service.gitUser.GitUserServiceImpl;
import com.company.statisticsservice.dto.RequestDto;
import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.mapper.*;

import com.company.statisticsservice.dto.statisticsResponse.StatisticsReportDto;
import com.company.statisticsservice.entity.*;
import com.company.statisticsservice.util.RestRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/statistics")
public class StatisticsController {


    private final GitUserServiceImpl gitUserService;
    private final CommitDensityServiceImpl commitDensityService;
    private final GitRepositoryServiceImpl gitRepositoryService;
    private final OrganizationServiceImpl organizationService;
    private final PullRequestReportServiceImpl pullRequestReportService;

    public StatisticsController(GitUserServiceImpl gitUserService, CommitDensityServiceImpl commitDensityService, GitRepositoryServiceImpl gitRepositoryService, OrganizationServiceImpl organizationService, PullRequestReportServiceImpl pullRequestReportService) {
        this.gitUserService = gitUserService;
        this.commitDensityService = commitDensityService;
        this.gitRepositoryService = gitRepositoryService;
        this.organizationService = organizationService;
        this.pullRequestReportService = pullRequestReportService;
    }

    @GetMapping("/{userID}/getReports")
    public List<StatisticsReportDto> getUserReport(@PathVariable Long userID) {
        return commitDensityService.getCommitDensityReports(userID);
    }

    @GetMapping()
    public List<StatisticsReportDto> getPRDensityTotal(@RequestBody RequestDto request) {

        RestRequest restRequest = RestRequest.getRestRequest();

        GitAccessResponseDto gitAccessResponseDto;
        List<StatisticsReportDto> reportDto = new ArrayList<>();
        StatisticsReportDto statisticsReportDto ;
        CommitDensity commitDensity;
        PullRequestReport pullRequestReport;

        Date since = request.getStartDate();
        Date till = request.getEndDate();
        String login = request.getLogin();
        String repo = request.getRepoName();
        String orgName = request.getOrganization();



        //Check request ,maybe DB have Report by that params ,
        // if DB don't have than creat request to GitAccess and get GiTAccessResponseDto
        // than creating user or repo or commit density report from dto and return the Report
        GitUser gitUser = gitUserService.isPresent(login);
        GitRepository repository;
        if (repo != null) {
            repository = gitRepositoryService.isPresent(repo);
        } else {
            repository = null;
        }
        GitOrganization organization = organizationService.isPresent(orgName);

        if (gitUser == null || repository == null || organization == null) {
            gitAccessResponseDto = restRequest.requestGitAccess(request);
            if (organization == null && orgName != null) {
                organization = organizationService.createOrganization(gitAccessResponseDto);
            }
            if (gitUser == null) {
                gitUser = gitUserService.createUser(gitAccessResponseDto, organization);
            }
            if (repository == null && repo != null) {
                repository = gitRepositoryService.createGitRepo(gitAccessResponseDto, gitUser, organization);
            }
        } else {
            commitDensity = commitDensityService.getReportByParamsOrg(organization, gitUser, since, till);
            pullRequestReport = pullRequestReportService.getPullRequestReportsByParamsOrg(organization, gitUser, since, till);
            statisticsReportDto = StatisticsReportDtoMapper.mapToDto(commitDensity,pullRequestReport);
            reportDto.add(statisticsReportDto);
        }

        if (reportDto.isEmpty() || reportDto.get(0) == null) {
            gitAccessResponseDto = restRequest.requestGitAccess(request);
            commitDensity = commitDensityService.creatReport(organization, gitUser, repository, gitAccessResponseDto);
            pullRequestReport = pullRequestReportService.createReport(organization, gitUser, repository, gitAccessResponseDto);
            //TODO Check why commitDensity not have userName
            statisticsReportDto = StatisticsReportDtoMapper.mapToDto(commitDensity,pullRequestReport);
            reportDto.add(statisticsReportDto);
        }
        return reportDto;
    }
}