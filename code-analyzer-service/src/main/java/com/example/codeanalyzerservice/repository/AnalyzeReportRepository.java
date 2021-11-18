package com.example.codeanalyzerservice.repository;

import com.example.codeanalyzerservice.entity.AnalyzeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeReportRepository extends JpaRepository<AnalyzeReport, Long> {
}
