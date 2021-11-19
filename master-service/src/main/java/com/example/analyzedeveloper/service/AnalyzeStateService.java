package com.example.analyzedeveloper.service;

import com.example.analyzedeveloper.dto.AnalyzeStateDTO;
import com.example.analyzedeveloper.entity.AnalyzeState;

import java.util.UUID;

public interface AnalyzeStateService {
    AnalyzeStateDTO get(UUID jobId);
    AnalyzeState save(AnalyzeState state);
     void delete(UUID jobId);

}
