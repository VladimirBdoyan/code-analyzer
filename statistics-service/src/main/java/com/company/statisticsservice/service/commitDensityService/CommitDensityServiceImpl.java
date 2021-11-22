package com.company.statisticsservice.service.commitDensityService;

import com.company.statisticsservice.dto.statisticsResponse.StatisticsReportDto;
import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.entity.CommitDensity;
import com.company.statisticsservice.entity.GitOrganization;
import com.company.statisticsservice.mapper.*;
import com.company.statisticsservice.entity.GitRepository;
import com.company.statisticsservice.entity.GitUser;
import com.company.statisticsservice.repository.CommitDensityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommitDensityServiceImpl implements CommitDensityService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommitDensityServiceImpl.class);

    private final CommitDensityRepository commitDensityRepository;

    public CommitDensityServiceImpl(CommitDensityRepository commitDensityRepository) {
        this.commitDensityRepository = commitDensityRepository;
    }
    @Override
    @Transactional
    public List<StatisticsReportDto> getCommitDensityReports(Long userId) {
        List<StatisticsReportDto> commitReposts;
        commitReposts = commitDensityRepository.getCommitReporstByUserId(userId);
        return commitReposts;
    }
    @Override
    @Transactional
    public StatisticsReportDto getReportByParams(Long userId, Long repoId, Date since, Date till) {

        List<StatisticsReportDto> commitReport = new ArrayList<>(
                commitDensityRepository.getCommitReport(userId,repoId, since, till));
        if(commitReport.isEmpty()){
            return null;
        }
        return commitReport.get(0);
    }
    @Override
    @Transactional
    public CommitDensity getReportByParamsOrg(GitOrganization organization,GitUser user, Date since, Date till) {
        LOGGER.info("Started getting Commit Density Report from Database");
        CommitDensity report = commitDensityRepository.getCommitDensityByUserNameAndOrganizationAndStartDateAndEndDate(user,organization, since, till);
        LOGGER.info("Finished getting Commit Density Report from Database");
        return report;
    }

    @Override
    @Transactional
    public StatisticsReportDto getReportById(Long id){
        CommitDensity commitDensity;
        StatisticsReportDto commitReport;
        commitDensity = commitDensityRepository.getById(id);
        commitReport = CommitDensityMapper.mapToDto(commitDensity).get();
        return commitReport;
    }
    @Override
    @Transactional
    public CommitDensity creatReport(GitOrganization organization,GitUser gitUser, GitRepository repo,
                                     GitAccessResponseDto dto) {
        LOGGER.info("Started creating Commit Density Report");
        CommitDensity commitDensity = CommitDensityMapper.mapToEntity(dto).get();
        commitDensity.setUserName(gitUser);
        commitDensity.setOrganization(organization);
        if(repo != null){
            commitDensity.setRepoName(repo);
        }
        commitDensity.setRepoName(repo);
        commitDensity = commitDensityRepository.save(commitDensity);
        LOGGER.info("Finished creating Commit Density Report");
        return commitDensity;
    }
}
