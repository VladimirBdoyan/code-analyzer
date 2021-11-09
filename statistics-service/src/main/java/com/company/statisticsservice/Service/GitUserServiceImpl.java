package com.company.statisticsservice.Service;


import com.company.statisticsservice.dto.GitUserDto;
import com.company.statisticsservice.dto.mapper.GitUserDtoMapper;
import com.company.statisticsservice.entity.GitUser;
import com.company.statisticsservice.repository.GitUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service

@Transactional
public class GitUserServiceImpl implements GitUserService {
    RestTemplate restTemplate = new RestTemplate();

    private final GitUserRepository gitUserRepository;

    @Autowired
    public GitUserServiceImpl(GitUserRepository gitUserRepository) {
        this.gitUserRepository = gitUserRepository;
    }

    @Override
    public Optional<GitUserDto> create(GitUserDto dto) {

        Optional<GitUser> gitUser = GitUserDtoMapper.mapToEntity(dto);

        gitUser = Optional.of(gitUserRepository.save(gitUser.get()));

        Optional<GitUserDto> dtoResponse;
        dtoResponse = GitUserDtoMapper.mapToDto(gitUser.orElse(null));
        dtoResponse.get().setRepoName(Objects.requireNonNull(gitUser.get().getRepositories()
                .stream()
                .filter(repo -> dto.getRepoName().equals(repo.getName()))
                .findAny().orElse(null)).getName());

        return GitUserDtoMapper.mapToDto(gitUser.orElse(null));
    }
}
