package com.company.statisticsservice.repository;

import com.company.statisticsservice.dto.statisticsResponse.StatisticsReportDto;
import com.company.statisticsservice.entity.CommitDensity;
import com.company.statisticsservice.entity.GitOrganization;
import com.company.statisticsservice.entity.GitUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface CommitDensityRepository extends JpaRepository<CommitDensity, Long>, JpaSpecificationExecutor<CommitDensity> {

    CommitDensity getCommitDensityByUserNameAndOrganizationAndStartDateAndEndDate(GitUser userName, GitOrganization organization, Date since, Date till);


    @Query(value = "SELECT gu.name AS username," +
            " gr.name AS repoName, cf.density, " +
            "cf.start_date, cf.end_date from commitfrequency cf" +
            " inner join githubrepository gr on gr.id = cf.repo_id" +
            " inner join githubuser gu on gu.id = cf.user_id where gr.name IN :grName " +
            "and gu.login IN :guLogin" +
            " and cf.start_date IN :since and cf.end_date IN :till ", nativeQuery = true)
    Collection<CommitDensity> getCommitReportById(@Param("grName") String grName, @Param("guLogin") String guLogin,
                                                  @Param("since") Timestamp since, @Param("till") Timestamp till);

    @Query("SELECT new com.company.statisticsservice.dto.statisticsResponse.StatisticsReportDto (go.name, gu.name, gr.name,cd.density," +
            "cd.startDate, cd.endDate)" +
            " FROM CommitDensity cd " +
            "inner join cd.repoName gr " +
            "inner join cd.organization go " +
            "inner join cd.userName gu " +
            "where gu.id =:userId ")
    List<StatisticsReportDto> getCommitReporstByUserId(@Param("userId") Long userId);

    @Query("SELECT new com.company.statisticsservice.dto.statisticsResponse.StatisticsReportDto (go.name,gu.name, gr.name,cd.density," +
            "cd.startDate, cd.endDate)" +
            " FROM CommitDensity cd " +
            "inner join cd.repoName gr " +
            "inner join cd.userName gu " +
            "inner join cd.organization go " +
            "where gr.id =:repoId " +
            "AND gu.id =:userId " +
            "AND cd.startDate <=:since AND cd.endDate >=:till"
            )
    List<StatisticsReportDto> getCommitReport(
            @Param("userId") Long userId,
            @Param("repoId") Long repoId,
            @Param("since") Date since,
            @Param("till") Date till);




}
