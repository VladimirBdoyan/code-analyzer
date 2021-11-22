package com.company.statisticsservice.service.gitPullRequest;

import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.entity.GitOrganization;
import com.company.statisticsservice.entity.GitRepository;
import com.company.statisticsservice.entity.GitUser;
import com.company.statisticsservice.entity.PullRequestReport;
import com.company.statisticsservice.mapper.*;
import com.company.statisticsservice.repository.PullRequestRepository;
import com.company.statisticsservice.service.gitOrganizationService.OrganizationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Service
public class PullRequestReportServiceImpl implements PullRequestReportService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PullRequestReportServiceImpl.class);

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
        LOGGER.info("Started creating PullRequest Report");

        PullRequestReport pullRequestsReport = PullRequestReportMapper.mapToEntity(dto);
        pullRequestsReport.setGitUser(gitUser);
        pullRequestsReport.setOrganization(organization);
        if(repo != null){
            pullRequestsReport.setGitRepository(repo);
        }
        pullRequestsReport = pullRequestRepository.save(pullRequestsReport);
        LOGGER.info("Finished creating PullRequest Report");
        return pullRequestsReport;
    }
}
