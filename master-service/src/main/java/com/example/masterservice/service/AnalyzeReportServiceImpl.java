package com.example.masterservice.service;

import com.example.masterservice.dto.AnalyzeReportDTO;
import com.example.masterservice.dto.AnalyzeRequestDTO;
import com.example.masterservice.dto.StatisticsReportDto;
import com.example.masterservice.dto.codeAnalyzerResponse.AnalyzeServiceReportDTO;
import com.example.masterservice.dto.mapper.AnalyzeReportMapper;
import com.example.masterservice.entity.AnalyzeReport;
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

        StatisticsReportDto statisticsReport = restTemplate.postForEntity(RequestURL.STATISTICSURLPATH, analyzeRequest, StatisticsReportDto.class).getBody();
        AnalyzeServiceReportDTO codeAnalyzerReport = restTemplate.postForEntity(RequestURL.CODEANALYZERPATH, analyzeRequest, AnalyzeServiceReportDTO.class).getBody();

        assert codeAnalyzerReport != null;
        assert statisticsReport != null;
        AnalyzeReport analyzeReport = AnalyzeReportMapper.mapToEntity(codeAnalyzerReport, statisticsReport);

        analyzeReport = analyzeReportRepository.save(analyzeReport);
        AnalyzeReportDTO reportDTO = AnalyzeReportMapper.mapToDto(analyzeReport);
//create report----------------------------------------------
        return analyzeReport;
    }
}
