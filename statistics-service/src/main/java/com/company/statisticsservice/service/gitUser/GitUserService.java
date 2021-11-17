package com.company.statisticsservice.service.gitUser;

import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.entity.GitOrganization;
import com.company.statisticsservice.entity.GitUser;

public interface GitUserService {
    GitUser createUser(GitAccessResponseDto dto, GitOrganization organization);
    GitUser isPresent(String Login);
}
