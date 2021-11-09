package com.company.statisticsservice.dto.mapper;

import com.company.statisticsservice.dto.GitUserDto;
import com.company.statisticsservice.entity.GitRepository;
import com.company.statisticsservice.entity.GitUser;
import com.company.statisticsservice.repository.GitUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GitUserDtoMapper {

    @Autowired
    GitUserRepository repository;

    public static Optional<GitUserDto> mapToDto(GitUser entity) {
        if (entity == null) {
            return Optional.empty();
        }
        GitUserDto dto = new GitUserDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLogin(entity.getLogin());
        return Optional.of(dto);
    }

    public static Optional<GitUser> mapToEntity(GitUserDto dto) {
        if (dto == null) {
            return Optional.empty();
        }
        List<GitRepository> repos = new ArrayList<>();
        repos.add(GitRepositoryMapper.mapToEntity(dto).get());
        GitUser entity = new GitUser();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setLogin(dto.getLogin());
        entity.setRepositories(repos);

        return Optional.of(entity);
    }
}
