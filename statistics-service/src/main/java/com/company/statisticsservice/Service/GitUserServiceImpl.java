package com.company.statisticsservice.Service;


import com.company.statisticsservice.dto.GitAccessResponseDto;
import com.company.statisticsservice.dto.mapper.CommitDensityMapper;
import com.company.statisticsservice.dto.mapper.GitRepositoryMapper;
import com.company.statisticsservice.dto.mapper.GitUserDtoMapper;
import com.company.statisticsservice.entity.CommitDensity;
import com.company.statisticsservice.entity.GitRepository;
import com.company.statisticsservice.entity.GitUser;
import com.company.statisticsservice.repository.CommitDensityRepository;
import com.company.statisticsservice.repository.GitHubRepository;
import com.company.statisticsservice.repository.GitUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GitUserServiceImpl implements GitUserService {

    private final GitUserRepository gitUserRepository;
    private final CommitDensityRepository commitDensityRepository;
    private final GitHubRepository gitHubRepository;

    @Autowired
    public GitUserServiceImpl(GitUserRepository gitUserRepository, CommitDensityRepository commitDensityRepository, GitHubRepository gitHubRepository) {
        this.gitUserRepository = gitUserRepository;
        this.commitDensityRepository = commitDensityRepository;
        this.gitHubRepository = gitHubRepository;
    }


    @Override
    @Transactional
    public Optional<GitAccessResponseDto> create(GitAccessResponseDto dto) {

        //TODO fix code style Optional object don't get in save method
        Optional<GitUser> gitUser = GitUserDtoMapper.mapToEntity(dto);
        CommitDensity commitDensity = CommitDensityMapper.mapToEntity(dto).get();
        GitRepository repository = GitRepositoryMapper.mapToEntity(dto).get();

        gitUser = Optional.of(gitUserRepository.save(gitUser.get()));
        repository.setGitUser(gitUser.get());
        repository = gitHubRepository.save(repository);
        commitDensity.setUserName(gitUser.get());
        commitDensity.setRepoName(repository);
        commitDensity = commitDensityRepository.save(commitDensity);

        Optional<GitAccessResponseDto> dtoResponse;
        dtoResponse = CommitDensityMapper.mapToDto(commitDensity);

        GitUser user = gitUserRepository.getById(gitUser.get().getId());

        return dtoResponse;
    }

}
