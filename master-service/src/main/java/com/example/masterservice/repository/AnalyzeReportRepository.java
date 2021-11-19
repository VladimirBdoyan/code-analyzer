package com.example.masterservice.repository;

import com.example.masterservice.entity.AnalyzeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeReportRepository extends JpaRepository<AnalyzeReport,Long> {
}
