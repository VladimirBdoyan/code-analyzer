package com.company.statisticsservice.dto.mapper;

import com.company.statisticsservice.dto.GitAccessResponseDto;
import com.company.statisticsservice.entity.GitRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GitRepositoryMapper {

    public static List<GitRepository> mapToEntities(List<GitAccessResponseDto> dtos) {

        List<GitRepository> repos = new ArrayList<>();

        for (GitAccessResponseDto dto : dtos) {
            repos.add(mapToEntity(dto).get());
        }
        return repos;
    }

    public static Optional<GitRepository> mapToEntity(GitAccessResponseDto dto) {
        if (dto == null) {
            return Optional.empty();
        }
        GitRepository entity = new GitRepository();
        entity.setId(dto.getId());
        entity.setName(dto.getRepoName());
//        entity.setGitUser(GitUserDtoMapper.mapToEntity(dto).get());
        return Optional.of(entity);
    }
}
