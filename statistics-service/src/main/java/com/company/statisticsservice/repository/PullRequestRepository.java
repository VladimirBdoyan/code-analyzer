package com.company.statisticsservice.repository;

import com.company.statisticsservice.entity.GitOrganization;
import com.company.statisticsservice.entity.GitUser;
import com.company.statisticsservice.entity.PullRequestReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PullRequestRepository extends JpaRepository<PullRequestReport, Long>, JpaSpecificationExecutor<PullRequestReport> {
    PullRequestReport getPullRequestReportsByGitUser (GitUser user);
    PullRequestReport getPullRequestReportByGitUserAndOrganizationAndStartDateAndEndDate(GitUser userName, GitOrganization organization, Date since, Date till);
}
