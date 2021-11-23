package com.example.codeanalyzerservice.service;

import com.example.codeanalyzerservice.dto.AnalyzeResultDTO;
import com.example.codeanalyzerservice.entity.AnalyzeResult;

public interface AnalyzeResultService {
    AnalyzeResultDTO save(AnalyzeResult analyzeResult);
    AnalyzeResultDTO get(Long id);
    void delete(Long id);
}
