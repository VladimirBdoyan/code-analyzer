package com.company.statisticsservice.mapper;

import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
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
        dto.setUserName(entity.getName());
        dto.setLogin(entity.getLogin());
        return Optional.of(dto);
    }

    public static Optional<GitUser> mapToEntity(GitAccessResponseDto dto) {
        if (dto == null) {
            return Optional.empty();
        }
        GitUser entity = new GitUser();
        entity.setId(dto.getId());
        entity.setName(dto.getUserName());
        entity.setLogin(dto.getLogin());

        return Optional.of(entity);
    }
}
