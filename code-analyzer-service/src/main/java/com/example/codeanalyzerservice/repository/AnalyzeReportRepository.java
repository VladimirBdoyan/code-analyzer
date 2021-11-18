package com.example.findbugs.repository;

import com.example.findbugs.entity.AnalyzeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeReportRepository extends JpaRepository<AnalyzeReport, Long> {
}
