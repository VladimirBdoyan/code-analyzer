package com.company.statisticsservice.dto.mapper;

import com.company.statisticsservice.dto.GitUserDto;
import com.company.statisticsservice.entity.GitRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GitRepositoryMapper {

    public static List<GitRepository> mapToEntities(List<GitUserDto> dtos) {

        List<GitRepository> repos = new ArrayList<>();

        for (GitUserDto dto : dtos) {
            repos.add(mapToEntity(dto).get());
        }
        return repos;
    }

    public static Optional<GitRepository> mapToEntity(GitUserDto dto) {
        if (dto == null) {
            return Optional.empty();
        }
        GitRepository entity = new GitRepository();
        entity.setName(dto.getRepoName());
        return Optional.of(entity);
    }
}
