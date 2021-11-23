package com.example.codeanalyzerservice.service;

import com.example.codeanalyzerservice.dto.AnalyzeReportDTO;
import com.example.codeanalyzerservice.entity.AnalyzeReport;

public interface AnalyzeReportService {
    AnalyzeReportDTO save(AnalyzeReport analyzeReport);
    AnalyzeReportDTO get(Long id);
    void delete(Long id);
}
