package com.example.codeanalyzerservice.service;

import com.example.codeanalyzerservice.entity.AnalyzeReport;
import com.example.codeanalyzerservice.exception.ResourceNotFoundException;
import com.example.codeanalyzerservice.repository.AnalyzeReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
                .orElseThrow(() ->
                     new ResourceNotFoundException("AnalyzeReport with id " + id + " not found")
                );
    }
}
