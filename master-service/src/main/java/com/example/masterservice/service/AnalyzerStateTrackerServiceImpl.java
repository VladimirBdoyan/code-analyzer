package com.example.masterservice.service;

import com.example.masterservice.dto.AnalyzeStateDTO;
import com.example.masterservice.entity.AnalyzeState;
import com.example.masterservice.entity.enums.ReportStatus;
import com.example.masterservice.repository.AnalyzeStateRepository;
import com.example.masterservice.service.model.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AnalyzerStateTrackerServiceImpl implements AnalyzerStateTrackerService {

    private final AnalyzeStateService analyzeStateService;

    @Override
    public AnalyzeState init(UUID jobId) {
        AnalyzeState analyzeState = new AnalyzeState();
        analyzeState.setJobId(jobId);
        analyzeState.setStatus(ReportStatus.IN_PROGRESS);
        analyzeState.setStartDate(new Date());
        return analyzeStateService.save(analyzeState);
    }

    @Override
    public void fail(AnalyzeState analyzeState) {
        analyzeState.setStatus(ReportStatus.FAILED);
        analyzeState.setFinishDate(new Date());
        analyzeStateService.save(analyzeState);
    }

    @Override
    public void finish(AnalyzeState analyzeState) {
        analyzeState.setStatus(ReportStatus.READY);
        analyzeState.setFinishDate(new Date());
        analyzeStateService.save(analyzeState);
    }
}
