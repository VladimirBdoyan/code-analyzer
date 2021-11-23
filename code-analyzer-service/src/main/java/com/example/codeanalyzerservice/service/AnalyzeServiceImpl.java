package com.example.codeanalyzerservice.service;

import com.example.codeanalyzerservice.analyzer.Visitor;
import com.example.codeanalyzerservice.dto.AnalyzeReportDTO;
import com.example.codeanalyzerservice.dto.AnalyzeRequestDTO;
import com.example.codeanalyzerservice.dto.GitAccessResponseDTO;
import com.example.codeanalyzerservice.entity.AnalyzeReport;
import com.example.codeanalyzerservice.entity.AnalyzeResult;
import com.example.codeanalyzerservice.exception.CodeAnalyzerRuntimeException;
import com.example.codeanalyzerservice.exception.RepositoryNotFoundException;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AnalyzeServiceImpl implements AnalyzeService {

    private final AnalyzeReportServiceImpl analyzeReportService;
    private final RestTemplateBuilder restTemplateBuilder;
    private final Visitor visitor;

    @Value("${gitAccessService}")
    private String gitAccessServiceUrl;

    @Override
    public AnalyzeReportDTO analyze(AnalyzeRequestDTO analyzeRequest) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        GitAccessResponseDTO response = restTemplate
                .postForObject(gitAccessServiceUrl, analyzeRequest, GitAccessResponseDTO.class);

        if (response == null) {
            throw new RepositoryNotFoundException(
                    "Repository for user: " + analyzeRequest.getUsername() + " not found");
        }

        List<URL> urls = response.getUrls();

        AnalyzeResult analyzeResult = new AnalyzeResult();

        for (URL url : urls) {
            try {
                JavaParser javaParser = new JavaParser();
                ReflectionTypeSolver reflectionTypeSolver = new ReflectionTypeSolver();
                JavaSymbolSolver javaSymbolSolver = new JavaSymbolSolver(reflectionTypeSolver);
                javaParser.getParserConfiguration().setSymbolResolver(javaSymbolSolver);
                CompilationUnit compilationUnit = javaParser.parse(
                        url.openStream()).getResult().orElseThrow(RuntimeException::new);
                visitor.visit(compilationUnit, analyzeResult);
            } catch (IOException e) {
                throw new CodeAnalyzerRuntimeException(e.getMessage());
            }
        }

        AnalyzeReport analyzeReport = new AnalyzeReport();
        analyzeReport.setUsername(analyzeRequest.getUsername());
        analyzeReport.setAnalyzeResult(analyzeResult);
        analyzeReport.setCreatedAt(new Date());
        analyzeReport.setStartDate(analyzeRequest.getStartDate());
        analyzeReport.setEndDate(analyzeRequest.getEndDate());
        return analyzeReportService.save(analyzeReport);
    }
}
