package com.example.masterservice.service;

import com.example.masterservice.dto.AnalyzeRequestDTO;
import com.example.masterservice.entity.AnalyzeReport;
import com.example.masterservice.entity.AnalyzeState;
import com.example.masterservice.repository.AnalyzeReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnalyzerServiceImpl implements AnalyzeService {
    private final AnalyzeReportService analyzerReportService;
    private final AnalyzerStateTrackerService analyzerStateTrackerService;
    private final AnalyzeReportRepository analyzeReportRepository;

    @Async
    @Transactional
    @Override
    public void analyze(UUID jobId, AnalyzeRequestDTO analyzeRequest) {
        AnalyzeReport analyzeReport = null;
        AnalyzeState analyzeState = null;

        try {
            analyzeState = analyzerStateTrackerService.init(jobId);
            analyzeReport = analyzerReportService.collectData(analyzeRequest);
        } catch (RuntimeException e) {
            analyzeState.setFailReason(e.getMessage());
            analyzerStateTrackerService.fail(analyzeState);
        }
            analyzeState.setAnalyzeReport(analyzeReport);
            analyzerStateTrackerService.finish(analyzeState);
    }
}
