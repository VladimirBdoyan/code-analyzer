package com.company.statisticsservice.repository;

import com.company.statisticsservice.entity.GitOrganization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface GitOrganizationRepository extends JpaRepository<GitOrganization, Long>, JpaSpecificationExecutor<GitOrganization> {
    GitOrganization findFirstByName(String orgName);
}
