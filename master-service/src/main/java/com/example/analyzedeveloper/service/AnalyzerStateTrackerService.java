package com.example.analyzedeveloper.service;


import com.example.analyzedeveloper.entity.AnalyzeState;

import java.util.UUID;

public interface AnalyzerStateTrackerService {
    AnalyzeState init(UUID jobId);
    void fail(AnalyzeState analyzeState);
    void finish(AnalyzeState analyzeState);
}
