package com.company.gitaccessservice.service.commit;

import com.company.gitaccessservice.dto.StatisticsResponseDto;
import com.company.gitaccessservice.dto.commit.GitAccessCommitDetailsDto;
import com.company.gitaccessservice.dto.commit.RequestDto;
import com.company.gitaccessservice.dto.pullRequest.GitAccessPullRequestDetailsDto;
import com.company.gitaccessservice.service.comment.CommentCalcServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommitServiceImpl {
    private final StatisticsResponseDto reportDto = new StatisticsResponseDto();

    private final DensityServiceImpl commitCalcService;
    private final CommentCalcServiceImpl commentCalcService;

    public CommitServiceImpl(DensityServiceImpl commitCalcService, CommentCalcServiceImpl commentCalcService) {
        this.commitCalcService = commitCalcService;
        this.commentCalcService = commentCalcService;
    }


    public StatisticsResponseDto createGitUserReport(RequestDto request){
        GitAccessCommitDetailsDto gitAccessCommitDetailsDto;
        GitAccessPullRequestDetailsDto pullRequestsDetailsDTO;

        //by user whole
        if(request.getRepoName() == null && request.getOrganization() == null ){
            gitAccessCommitDetailsDto = commitCalcService.getCommitDensityByUser(request);
            pullRequestsDetailsDTO = commentCalcService.creatGitUserPullRequestDetailsByUser(request);
            //by user repo
        }else if (request.getRepoName() != null && request.getOrganization() == null){
            gitAccessCommitDetailsDto = commitCalcService.getCommitDensityByUserRepo(request);
            pullRequestsDetailsDTO = commentCalcService.creatGitUserPullRequestDetailsByUserRepo(request);
            //by Org whole
        }else if(request.getRepoName() == null && request.getOrganization() != null ){
            gitAccessCommitDetailsDto = commitCalcService.getCommitDensityByOrg(request);
            pullRequestsDetailsDTO = commentCalcService.creatGitUserPullRequestDetailsByOrg(request);
            //by Org repo
        }else{
            gitAccessCommitDetailsDto = commitCalcService.getCommitDensityByOrgRepo(request);
            pullRequestsDetailsDTO = commentCalcService.creatGitUserPullRequestDetailsByOrgRepo(request);
        }
        reportDto.setOrganization(request.getOrganization());
        reportDto.setRepoName(request.getRepoName());
        reportDto.setLogin(gitAccessCommitDetailsDto.getLogin());
        reportDto.setUserName(gitAccessCommitDetailsDto.getUserName());

        reportDto.setSince(request.getStartDate().getTime());
        reportDto.setTill(request.getEndDate().getTime());
        reportDto.setGitUserPullRequestsDetailsDTO(pullRequestsDetailsDTO);
        reportDto.setCommitFrequencyDTO(gitAccessCommitDetailsDto);
        return reportDto;
    }
}
