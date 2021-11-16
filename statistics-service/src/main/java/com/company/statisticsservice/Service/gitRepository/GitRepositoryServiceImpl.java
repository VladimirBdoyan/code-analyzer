package com.company.statisticsservice.Service.gitRepository;

import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.entity.GitOrganization;
import com.company.statisticsservice.mapper.*;
import com.company.statisticsservice.entity.GitRepository;
import com.company.statisticsservice.entity.GitUser;
import com.company.statisticsservice.repository.GitHubRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GitRepositoryServiceImpl implements GitRepositoryService {
    private final GitHubRepository gitHubRepository;

    public GitRepositoryServiceImpl(GitHubRepository gitHubRepository) {
        this.gitHubRepository = gitHubRepository;
    }

    @Override
    @Transactional
    public GitRepository isPresent(String name) {
        if(name == null){
            return null;
        }
        GitRepository repository ;
        try {
            repository = gitHubRepository.findFirstByName(name);
        } catch (NullPointerException e) {
            return null;
        }
        return repository;
    }

    @Override
    @Transactional
    public GitRepository createGitRepo(GitAccessResponseDto dto, GitUser gitUser, GitOrganization organization) {
        GitRepository gitRepo = GitRepositoryMapper.mapToEntity(dto).get();
        gitRepo.setGitUser(gitUser);
        gitRepo.setOrganization(organization);
        gitRepo = gitHubRepository.save(gitRepo);
        return gitRepo;
    }
}
