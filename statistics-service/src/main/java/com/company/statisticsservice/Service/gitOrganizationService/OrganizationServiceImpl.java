package com.company.statisticsservice.Service.gitOrganizationService;

import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.entity.GitOrganization;
import com.company.statisticsservice.repository.GitOrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    private final  GitOrganizationRepository organizationRepository;

    public OrganizationServiceImpl(GitOrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    @Transactional
    public GitOrganization isPresent(String name) {
        GitOrganization organization = null;
        organization = organizationRepository.findFirstByName(name);
        return organization;
    }

    @Override
    @Transactional
    public GitOrganization createOrganization(GitAccessResponseDto dto) {
        GitOrganization organization = new GitOrganization(dto.getOrganization());
        return organizationRepository.save(organization);
    }
}
