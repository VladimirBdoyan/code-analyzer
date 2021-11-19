package com.example.analyzedeveloper.service;

import com.example.analyzedeveloper.dto.AnalyzeRequestDTO;

import java.util.UUID;

public interface AnalyzeService {
    void analyze(UUID jobId, AnalyzeRequestDTO analyzeRequest);
}
