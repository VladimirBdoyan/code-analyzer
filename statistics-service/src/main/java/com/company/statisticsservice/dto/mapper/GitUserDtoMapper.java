package com.company.statisticsservice.dto.mapper;

import com.company.statisticsservice.dto.GitAccessResponseDto;
import com.company.statisticsservice.entity.GitUser;
import com.company.statisticsservice.repository.GitUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class GitUserDtoMapper {

    @Autowired
    GitUserRepository repository;

    public static Optional<GitAccessResponseDto> mapToDto(GitUser entity) {
        if (entity == null) {
            return Optional.empty();
        }
        GitAccessResponseDto dto = new GitAccessResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLogin(entity.getLogin());
        return Optional.of(dto);
    }

    public static Optional<GitUser> mapToEntity(GitAccessResponseDto dto) {
        if (dto == null) {
            return Optional.empty();
        }
//        List<GitRepository> repos = new ArrayList<>();
//        repos.add(GitRepositoryMapper.mapToEntity(dto).get());
//        List<CommitDensity> commitReport = new ArrayList<>();
//        commitReport.add(CommitDensityMapper.mapToEntity(dto).get());
        GitUser entity = new GitUser();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setLogin(dto.getLogin());
//        entity.setRepositories(repos);
//        entity.setCommitReport(commitReport);

        return Optional.of(entity);
    }
}
