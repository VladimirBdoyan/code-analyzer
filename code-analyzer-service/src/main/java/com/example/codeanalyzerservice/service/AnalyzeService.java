package com.example.codeanalyzerservice.service;

import com.example.codeanalyzerservice.analyzer.Visitor;
import com.example.codeanalyzerservice.dto.AnalyzeReportDTO;
import com.example.codeanalyzerservice.dto.AnalyzeRequestDTO;
import com.example.codeanalyzerservice.dto.GitAccessResponseDTO;
import com.example.codeanalyzerservice.dto.mapper.AnalyzeReportMapper;
import com.example.codeanalyzerservice.entity.AnalyzeReport;
import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.exception.RepositoriesNotFoundException;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AnalyzeService {

    private final AnalyzeReportService analyzeReportService;
    private final RestTemplateBuilder restTemplateBuilder;
    private final Visitor visitor;

    @Transactional
    public AnalyzeReportDTO analyze(AnalyzeRequestDTO analyzeRequest) {
        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<List<URL>> response = restTemplate.exchange("http://localhost:8080/api/v1/git-access/URL", HttpMethod.POST, null,
//                new ParameterizedTypeReference<List<URL>>() {
//                }, analyzeRequest);
        GitAccessResponseDTO response = restTemplate.postForObject("http://localhost:8080/api/v1/git-access/URL",analyzeRequest,GitAccessResponseDTO.class);


//        if (response.getStatusCode().is4xxClientError()) {
//            throw new RepositoriesNotFoundException(
//                    "Repositories for user: " + analyzeRequest.getLogin() + " not found");
//        }

        List<URL> urls = response.getUrls();

        if (urls == null) {
            throw new RepositoriesNotFoundException(
                    "Repositories for user: " + analyzeRequest.getLogin() + " not found");
        }


        AnalyzeResult analyzeResult=new AnalyzeResult();

        for (URL url : urls) {
            try {
                JavaParser javaParser = new JavaParser();
                ReflectionTypeSolver reflectionTypeSolver = new ReflectionTypeSolver();
                JavaSymbolSolver javaSymbolSolver = new JavaSymbolSolver(reflectionTypeSolver);
                javaParser.getParserConfiguration().setSymbolResolver(javaSymbolSolver);
                CompilationUnit compilationUnit = javaParser.parse(url.openStream()).getResult().orElseThrow(RuntimeException::new);
                visitor.visit(compilationUnit, analyzeResult);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        AnalyzeReport analyzeReport = new AnalyzeReport();
        analyzeReport.setAnalyzeResult(analyzeResult);
        analyzeReport.setDate(new Date());
        analyzeReport.setUsername(analyzeRequest.getLogin());
        analyzeReport.setSince(analyzeRequest.getStartDate());
        analyzeReport.setTill(analyzeRequest.getEndDate());
        analyzeReport = analyzeReportService.save(analyzeReport);
        AnalyzeReportDTO  reportDTO = new AnalyzeReportDTO();
        reportDTO = AnalyzeReportMapper.mapToDTO(analyzeReport);
        return reportDTO;

    }
}
