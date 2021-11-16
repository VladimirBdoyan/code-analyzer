package com.company.statisticsservice.Service.gitPullRequest;

import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.entity.GitOrganization;
import com.company.statisticsservice.entity.GitRepository;
import com.company.statisticsservice.entity.GitUser;
import com.company.statisticsservice.entity.PullRequestReport;
import com.company.statisticsservice.mapper.*;
import com.company.statisticsservice.repository.PullRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Service
public class PullRequestReportServiceImpl implements PullRequestReportService {

    private final PullRequestRepository pullRequestRepository;

    public PullRequestReportServiceImpl(PullRequestRepository pullRequestRepository) {
        this.pullRequestRepository = pullRequestRepository;
    }


    @Override
    public PullRequestReport getPullRequestReportsByUser(GitUser user) {

        return pullRequestRepository.getPullRequestReportsByGitUser(user);
    }

    @Override
    public PullRequestReport getPullRequestReportsByParamsOrg(GitOrganization organization,GitUser user, Date since, Date till) {
        return pullRequestRepository.getPullRequestReportByGitUserAndOrganizationAndStartDateAndEndDate(user,organization,since,till);
    }

    @Override
    @Transactional
    public PullRequestReport createReport(GitOrganization organization,
                                          GitUser gitUser,
                                          GitRepository repo,
                                          GitAccessResponseDto dto) {

        PullRequestReport pullRequestsReport = PullRequestReportMapper.mapToEntity(dto);
        pullRequestsReport.setGitUser(gitUser);
        pullRequestsReport.setOrganization(organization);
        if(repo != null){
            pullRequestsReport.setGitRepository(repo);
        }
        return pullRequestRepository.save(pullRequestsReport);
    }
}
