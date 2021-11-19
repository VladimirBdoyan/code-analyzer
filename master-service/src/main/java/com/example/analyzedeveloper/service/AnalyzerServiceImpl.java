package com.example.analyzedeveloper.service;

import com.example.analyzedeveloper.dto.AnalyzeRequestDTO;
import com.example.analyzedeveloper.entity.AnalyzeReport;
import com.example.analyzedeveloper.entity.AnalyzeState;
import com.example.analyzedeveloper.repository.AnalyzeReportRepository;
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
   // @Transactional
    @Override
    public void analyze(UUID jobId, AnalyzeRequestDTO analyzeRequest) {
        AnalyzeReport analyzeReport = null;
        AnalyzeState analyzeState = null;

        try {
            analyzeState = analyzerStateTrackerService.init(jobId);
            analyzeReport = analyzerReportService.collectData(analyzeRequest);
        } catch (RuntimeException e) {
            analyzerStateTrackerService.fail(analyzeState);
            e.printStackTrace();
        }
            analyzeState.setAnalyzeReport(analyzeReport);
            analyzerStateTrackerService.finish(analyzeState);
    }
}
