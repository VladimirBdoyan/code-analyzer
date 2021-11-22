package com.company.statisticsservice.service.gitOrganizationService;

import com.company.statisticsservice.dto.gitAccessResponse.GitAccessResponseDto;
import com.company.statisticsservice.entity.GitOrganization;
import com.company.statisticsservice.repository.GitOrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final GitOrganizationRepository organizationRepository;

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
        LOGGER.info("Started creating Organization");
        GitOrganization organization = new GitOrganization(dto.getOrganization());
        organization = organizationRepository.save(organization);
        LOGGER.info("Finished creating Organization {}", organization);
        return organization;
    }
}
