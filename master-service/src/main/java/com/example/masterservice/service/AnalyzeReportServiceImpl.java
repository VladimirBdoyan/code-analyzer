package com.example.masterservice.service;

import com.example.masterservice.dto.AnalyzeReportDTO;
import com.example.masterservice.dto.AnalyzeRequestDTO;
import com.example.masterservice.dto.StatisticsReportDTO;
import com.example.masterservice.dto.CodeAnalyzeReportDTO;
import com.example.masterservice.dto.mapper.AnalyzeReportMapper;
import com.example.masterservice.entity.AnalyzeReport;
import com.example.masterservice.exception.ResourceNotFoundException;
import com.example.masterservice.repository.AnalyzeReportRepository;
import com.example.masterservice.util.RequestURL;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AnalyzeReportServiceImpl implements AnalyzeReportService {

    private final AnalyzeReportRepository analyzeReportRepository;
    private final RestTemplateBuilder restTemplateBuilder;

    @Override
    public AnalyzeReport collectData(AnalyzeRequestDTO analyzeRequest) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        StatisticsReportDTO statisticsReport = restTemplate
                .postForEntity(RequestURL.STATISTICS_URL_PATH, analyzeRequest, StatisticsReportDTO.class).getBody();
        CodeAnalyzeReportDTO codeAnalyzerReport = restTemplate
                .postForEntity(RequestURL.CODE_ANALYZER_PATH, analyzeRequest, CodeAnalyzeReportDTO.class).getBody();

        if (statisticsReport == null || codeAnalyzerReport == null) {
            throw new ResourceNotFoundException("Account for user" + analyzeRequest.getLogin() + " not found");
        }

        AnalyzeReport analyzeReport =
                AnalyzeReportMapper.mapToEntity(codeAnalyzerReport, statisticsReport);

        analyzeReport = analyzeReportRepository.save(analyzeReport);
        AnalyzeReportDTO reportDTO = AnalyzeReportMapper.mapToDto(analyzeReport);
        return analyzeReport;
    }
}
