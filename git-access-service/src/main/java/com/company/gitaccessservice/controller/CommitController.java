package com.company.gitaccessservice.controller;

import com.company.gitaccessservice.dto.StatisticsResponseDto;
import com.company.gitaccessservice.dto.commit.GitAccessCommitDetailsDto;
import com.company.gitaccessservice.dto.commit.RequestDto;
import com.company.gitaccessservice.dto.pullRequest.GitAccessPullRequestDetailsDto;
import com.company.gitaccessservice.service.comment.CommentCalcServiceImpl;
import com.company.gitaccessservice.service.commit.DensityServiceImpl;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/git-access/density")
public class CommitController {

    private final DensityServiceImpl commitCalcService;
    private final CommentCalcServiceImpl commentCalcService;

    public CommitController(DensityServiceImpl commitCalcService, CommentCalcServiceImpl commentCalcService) {
        this.commitCalcService = commitCalcService;
        this.commentCalcService = commentCalcService;
    }

    @PostMapping()
    public StatisticsResponseDto getUserReport(@RequestBody RequestDto request) {

        GitAccessCommitDetailsDto gitAccessCommitDetailsDto;
        GitAccessPullRequestDetailsDto pullRequestsDetailsDTO;
        StatisticsResponseDto statisticsResponseDto = new StatisticsResponseDto();

        if(request.getRepoName() == null ){
            gitAccessCommitDetailsDto = commitCalcService.getCommitDensityByOrg(request);
            pullRequestsDetailsDTO = commentCalcService.creatGitUserPullRequestDetailsByOrg(request);
            statisticsResponseDto.setOrganization(request.getOrganization());
        }else{
            gitAccessCommitDetailsDto = commitCalcService.getCommitDensityByRepo(request);
            pullRequestsDetailsDTO = commentCalcService.creatGitUserPullRequestDetailsByRepo(request);
            statisticsResponseDto.setRepoName(request.getRepoName());
        }
        statisticsResponseDto.setOrganization(request.getOrganization());
        statisticsResponseDto.setLogin(gitAccessCommitDetailsDto.getLogin());
        statisticsResponseDto.setUserName(gitAccessCommitDetailsDto.getUserName());

        statisticsResponseDto.setSince(request.getStartDate().getTime());
        statisticsResponseDto.setTill(request.getEndDate().getTime());
        statisticsResponseDto.setGitUserPullRequestsDetailsDTO(pullRequestsDetailsDTO);
        statisticsResponseDto.setCommitFrequencyDTO(gitAccessCommitDetailsDto);

        return statisticsResponseDto;
    }
}
