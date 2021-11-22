package com.company.statisticsservice.service.gitOrganizationService;

import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.entity.GitOrganization;

public interface OrganizationService {
    GitOrganization isPresent(String name);
    GitOrganization createOrganization(GitAccessResponseDto dto);
}
