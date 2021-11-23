package com.example.codeanalyzerservice.service;

import com.example.codeanalyzerservice.dto.AnalyzeReportDTO;
import com.example.codeanalyzerservice.dto.AnalyzeRequestDTO;

public interface AnalyzeService {
    AnalyzeReportDTO analyze(AnalyzeRequestDTO analyzeRequest);
}
