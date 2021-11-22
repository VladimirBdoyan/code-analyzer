package com.example.security.repository;

import com.example.security.model.CodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<CodeEntity, Long> {
    CodeEntity findByUsername(String username);
}
