package com.example.masterservice.service;

import com.example.masterservice.dto.AnalyzeStateDTO;
import com.example.masterservice.entity.AnalyzeState;

import java.util.UUID;

public interface AnalyzeStateService {
    AnalyzeStateDTO get(UUID jobId);
    AnalyzeState save(AnalyzeState state);
    void delete(UUID jobId);
}
