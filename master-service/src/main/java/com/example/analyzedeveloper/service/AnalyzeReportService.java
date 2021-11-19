package com.example.analyzedeveloper.service;

import com.example.analyzedeveloper.dto.AnalyzeRequestDTO;
import com.example.analyzedeveloper.entity.AnalyzeReport;

public interface AnalyzeReportService {

    AnalyzeReport collectData(AnalyzeRequestDTO analyzeRequest);

}
