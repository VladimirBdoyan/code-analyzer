package com.example.analyzedeveloper.service;

import com.example.analyzedeveloper.dto.AnalyzeRequestDTO;
import com.example.analyzedeveloper.dto.StatisticsReportDto;
import com.example.analyzedeveloper.entity.AnalyzeReport;
import com.example.analyzedeveloper.repository.AnalyzeReportRepository;
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

        StatisticsReportDto statisticsReport = restTemplate.postForEntity("http://localhost:8085/api/v1/statistics", analyzeRequest, StatisticsReportDto.class).getBody();
        //CodeAnalyzeReport codeAnalyzerReport = restTemplate.getForEntity("uri", CodeAnalyzeReport.class, analyzeRequest).getBody();
        AnalyzeReport analyzeReport = new AnalyzeReport();
        //analyzeReport.setBugs(analyzeReport.getBugs());
        analyzeReport.setCodingRate(65);
        analyzeReport.setConversationCount(98);
        analyzeReport.setDeveloperCommitDensity(5);
//create report----------------------------------------------
        return  analyzeReport;
    }
}
