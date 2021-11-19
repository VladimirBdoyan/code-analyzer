package com.example.masterservice.service;

import com.example.masterservice.dto.AnalyzeRequestDTO;
import com.example.masterservice.entity.AnalyzeReport;

public interface AnalyzeReportService {

    AnalyzeReport collectData(AnalyzeRequestDTO analyzeRequest);

}
