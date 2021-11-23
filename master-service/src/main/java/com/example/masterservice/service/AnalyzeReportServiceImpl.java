package com.example.masterservice.service;

import com.example.masterservice.dto.AnalyzeRequestDTO;
import com.example.masterservice.dto.CodeAnalyzeReportDTO;
import com.example.masterservice.dto.StatisticsReportDTO;
import com.example.masterservice.dto.mapper.AnalyzeReportMapper;
import com.example.masterservice.entity.AnalyzeReport;
import com.example.masterservice.exception.ResourceNotFoundException;
import com.example.masterservice.repository.AnalyzeReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalyzeReportServiceImpl implements AnalyzeReportService {

    private static final String statisticsServiceUrl = "http://localhost:8085/api/v1/statistics";
    private static final String codeAnalyzerServiceUrl = "http://localhost:8086/api/analyze";

    private final AnalyzeReportRepository analyzeReportRepository;
    private final RestTemplateBuilder restTemplateBuilder;

    @Override
    public AnalyzeReport collectData(AnalyzeRequestDTO analyzeRequest) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        StatisticsReportDTO statisticsReport = restTemplate
                .postForEntity(statisticsServiceUrl, analyzeRequest, StatisticsReportDTO.class).getBody();
        CodeAnalyzeReportDTO codeAnalyzerReport = restTemplate
                .postForEntity(codeAnalyzerServiceUrl, analyzeRequest, CodeAnalyzeReportDTO.class).getBody();

        if (statisticsReport == null || codeAnalyzerReport == null) {
            throw new ResourceNotFoundException("Account for user" + analyzeRequest.getUsername() + " not found");
        }

        AnalyzeReport analyzeReport = AnalyzeReportMapper.mapToEntity(codeAnalyzerReport, statisticsReport);

        return analyzeReportRepository.save(analyzeReport);
    }
}
