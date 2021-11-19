package com.example.analyzedeveloper.repository;

import com.example.analyzedeveloper.entity.AnalyzeReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeReportRepository extends JpaRepository<AnalyzeReport,Long> {
}
