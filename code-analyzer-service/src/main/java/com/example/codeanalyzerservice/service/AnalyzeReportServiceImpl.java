package com.example.codeanalyzerservice.service;

import com.example.codeanalyzerservice.dto.AnalyzeReportDTO;
import com.example.codeanalyzerservice.dto.mapper.AnalyzeReportMapper;
import com.example.codeanalyzerservice.entity.AnalyzeReport;
import com.example.codeanalyzerservice.exception.ResourceNotFoundException;
import com.example.codeanalyzerservice.repository.AnalyzeReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnalyzeReportServiceImpl implements AnalyzeReportService {

    private final AnalyzeReportRepository analyzeReportRepository;

    @Override
    public AnalyzeReportDTO save(AnalyzeReport analyzeReport) {
        AnalyzeReport saved = analyzeReportRepository.save(analyzeReport);
        return AnalyzeReportMapper.mapToDTO(saved);
    }

    @Override
    public AnalyzeReportDTO get(Long id) {
        AnalyzeReport analyzeReport = analyzeReportRepository
                .findById(id)
                .orElseThrow(() ->
                     new ResourceNotFoundException("AnalyzeReport with id " + id + " not found")
                );

        return AnalyzeReportMapper.mapToDTO(analyzeReport);
    }

    @Override
    public void delete(Long id) {
        analyzeReportRepository.deleteById(id);
    }
}
