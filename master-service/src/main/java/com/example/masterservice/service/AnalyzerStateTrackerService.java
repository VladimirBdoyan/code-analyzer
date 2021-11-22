package com.example.masterservice.service;


import com.example.masterservice.entity.AnalyzeState;

import java.util.UUID;

public interface AnalyzerStateTrackerService {
    AnalyzeState init(UUID jobId);
    void fail(AnalyzeState analyzeState);
    void finish(AnalyzeState analyzeState);
}
