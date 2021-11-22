package com.example.masterservice.service;

import com.example.masterservice.dto.AnalyzeRequestDTO;

import java.util.UUID;

public interface AnalyzeService {
    void analyze(UUID jobId, AnalyzeRequestDTO analyzeRequest);
}
