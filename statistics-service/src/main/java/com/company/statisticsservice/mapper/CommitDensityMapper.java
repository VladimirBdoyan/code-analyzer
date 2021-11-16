package com.company.statisticsservice.mapper;

import com.company.statisticsservice.dto.gitAccessResponse.GitAccessCommitDetailsDto;
import com.company.statisticsservice.dto.statisticsResponse.CommitDensityDto;
import com.company.statisticsservice.dto.statisticsResponse.StatisticsReportDto;
import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.entity.CommitDensity;
import com.company.statisticsservice.util.Calculate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommitDensityMapper {

    public static List<CommitDensity> mapToEntities(List<GitAccessResponseDto> dtos){
        List<CommitDensity> commitDensities = new ArrayList<>();

        for (GitAccessResponseDto dto : dtos) {
            commitDensities.add(mapToEntity(dto).get());
        }
        return commitDensities;
    }


    public static Optional<CommitDensity> mapToEntity(GitAccessResponseDto dto) {

        if (dto == null) {
            return Optional.empty();
        }
        GitAccessCommitDetailsDto commitDetailsDto = dto.getCommitDetailsDto();
        //TODO change optional return get with checking isPresent
        CommitDensity entity = new CommitDensity();
        if(commitDetailsDto.getCommitCount() == 0){
            entity.setDensity(0.0);
        }else {
            entity.setDensity(Calculate.density(commitDetailsDto.getCommitCount()
                    , commitDetailsDto.getRepoCommitCount()));
        }

        entity.setStartDate(new Date(dto.getSince()));
        entity.setEndDate(new Date(dto.getTill()));

        return Optional.of(entity);
    }

    public static  Optional<StatisticsReportDto> mapToDto(CommitDensity entity) {
        if (entity == null) {
            return Optional.empty();
        }
        StatisticsReportDto dto = new StatisticsReportDto();
        SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/DD");
        dto.setUserName(entity.getUserName().getName());
        dto.setRepoName(entity.getRepoName().getName());
        dto.setDensity(entity.getDensity());
        dto.setSinceDate(format.format(entity.getStartDate()));
        dto.setTillDate(format.format(entity.getEndDate()));
        return Optional.of(dto);
    }

    public static CommitDensityDto mapToDtoDensity(CommitDensity commitDensity){
        CommitDensityDto commitDensityDto = new CommitDensityDto();
        commitDensityDto.setDensity(commitDensity.getDensity());
        return commitDensityDto;
    }

}
