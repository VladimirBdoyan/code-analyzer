package com.example.codeanalyzerservice.repository;

import com.example.codeanalyzerservice.entity.CodeSmell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeSmellRepository extends JpaRepository<CodeSmell, Long> {
}
