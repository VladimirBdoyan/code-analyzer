package com.example.findbugs.service;

import com.example.findbugs.entity.AnalyzeReport;
import com.example.findbugs.exception.ResourceNotFoundException;
import com.example.findbugs.repository.AnalyzeReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnalyzeReportService {

    private final AnalyzeReportRepository analyzeReportRepository;

    AnalyzeReport save(AnalyzeReport analyzeReport) {
        return analyzeReportRepository.save(analyzeReport);
    }

    AnalyzeReport getById(Long id) {
        return analyzeReportRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new ResourceNotFoundException("AnalyzeReport with id " + id + " not found");
                });
    }
}
