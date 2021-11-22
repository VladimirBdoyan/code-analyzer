package com.example.codeanalyzerservice.repository;

import com.example.codeanalyzerservice.entity.AnalyzeResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeResultRepository extends JpaRepository<AnalyzeResult,Long> {
}
