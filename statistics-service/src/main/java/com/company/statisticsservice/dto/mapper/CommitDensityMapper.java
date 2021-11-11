package com.company.statisticsservice.dto.mapper;

import com.company.statisticsservice.dto.GitAccessResponseDto;
import com.company.statisticsservice.entity.CommitDensity;
import com.company.statisticsservice.util.Calculate;

import java.sql.Timestamp;
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

        //TODO change optional return get with checking isPresent
        CommitDensity entity = new CommitDensity();
        entity.setId(dto.getId());
//        entity.setRepoName(GitRepositoryMapper.mapToEntity(dto).get());
//        entity.setUserName(GitUserDtoMapper.mapToEntity(dto).get());
        entity.setDensity(Calculate.density(dto.getCommitCount(), dto.getRepoCommitCount()));
        entity.setStartDate(new Timestamp(dto.getSince()));
        entity.setEndDate(new Timestamp(dto.getTill()));

        return Optional.of(entity);
    }

    public static  Optional<GitAccessResponseDto> mapToDto(CommitDensity entity) {
        if (entity == null) {
            return Optional.empty();
        }
        GitAccessResponseDto dto = new GitAccessResponseDto();
        dto.setId(entity.getId());
        return Optional.of(dto);
    }

}
