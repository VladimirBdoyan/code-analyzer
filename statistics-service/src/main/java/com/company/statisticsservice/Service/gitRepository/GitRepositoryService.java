package com.company.statisticsservice.Service.gitRepository;

import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.entity.GitOrganization;
import com.company.statisticsservice.entity.GitRepository;
import com.company.statisticsservice.entity.GitUser;

public interface GitRepositoryService {
    GitRepository isPresent(String name);
    GitRepository createGitRepo(GitAccessResponseDto dto, GitUser gitUser, GitOrganization organization);
}
