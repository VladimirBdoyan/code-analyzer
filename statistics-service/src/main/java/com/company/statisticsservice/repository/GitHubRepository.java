package com.company.statisticsservice.repository;

import com.company.statisticsservice.entity.GitRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GitHubRepository extends JpaRepository<GitRepository, Long>, JpaSpecificationExecutor<GitRepository> {

    GitRepository findFirstByName(String name);

}
