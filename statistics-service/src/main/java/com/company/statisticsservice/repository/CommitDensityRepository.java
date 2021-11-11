package com.company.statisticsservice.repository;

import com.company.statisticsservice.dto.CommitDensityReportDto;
import com.company.statisticsservice.entity.CommitDensity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Repository
public interface CommitDensityRepository extends JpaRepository<CommitDensity,Long>, JpaSpecificationExecutor<CommitDensity> {

    @Query(value = "SELECT gu.name AS username," +
            " gr.name AS repoName, cf.density, " +
            "cf.start_date, cf.end_date from commitfrequency cf" +
            " inner join githubrepository gr on gr.id = cf.repo_id" +
            " inner join githubuser gu on gu.id = cf.user_id where gr.name IN :grName " +
            "and gu.login IN :guLogin" +
            " and cf.start_date IN :since and cf.end_date IN :till ", nativeQuery = true)
    Collection<CommitDensity> getCommitReportById(@Param("grName") String grName, @Param("guLogin") String guLogin,
                                                  @Param("since") Timestamp since, @Param("till") Timestamp till);

    @Query("SELECT new com.company.statisticsservice.dto.CommitDensityReportDto (gr.name, gu.name,cd.density," +
            "cd.startDate, cd.endDate)" +
            " FROM CommitDensity cd " +
            "inner join cd.repoName gu " +
            "inner join cd.userName gr " +
            "where gr.id =:userId ")
    List<CommitDensityReportDto> getCommitReportById(@Param("userId") Long userId);
}
