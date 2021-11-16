package com.company.statisticsservice.repository;

import com.company.statisticsservice.entity.GitUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GitUserRepository extends JpaRepository<GitUser, Long>, JpaSpecificationExecutor<GitUser> {

    GitUser findFirstByLogin(String login);


}

