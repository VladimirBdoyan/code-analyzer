package com.example.findbugs.service;

import com.example.findbugs.analyzer.Visitor;
import com.example.findbugs.dto.AnalyzeRequestDTO;
import com.example.findbugs.entity.AnalyzeReport;
import com.example.findbugs.entity.AnalyzeResult;
import com.example.findbugs.exception.RepositoriesNotFoundException;
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
    public AnalyzeReport analyze(AnalyzeRequestDTO analyzeRequest) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List<URL>> response = restTemplate.exchange("url", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<URL>>() {
                }, analyzeRequest);

        if (response.getStatusCode().is4xxClientError()) {
            throw new RepositoriesNotFoundException(
                    "Repositories for user: " + analyzeRequest.getUsername() + " not found");
        }

        List<URL> urls = response.getBody();

        if (urls == null) {
            throw new RepositoriesNotFoundException(
                    "Repositories for user: " + analyzeRequest.getUsername() + " not found");
        }


        AnalyzeResult analyzeResult=new AnalyzeResult();

        for (URL url : urls) {
            try {
                JavaParser javaParser = new JavaParser();
                ReflectionTypeSolver reflectionTypeSolver = new ReflectionTypeSolver();
                JavaSymbolSolver javaSymbolSolver = new JavaSymbolSolver(reflectionTypeSolver);
                javaParser.getParserConfiguration().setSymbolResolver(javaSymbolSolver);
                CompilationUnit compilationUnit = javaParser.parse(url.openStream()).getResult().orElseThrow();
                visitor.visit(compilationUnit, analyzeResult);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        AnalyzeReport analyzeReport = new AnalyzeReport();
        analyzeReport.setAnalyzeResult(analyzeResult);
        analyzeReport.setDate(new Date());
        analyzeReport.setUsername(analyzeRequest.getUsername());
        analyzeReport.setSince(analyzeRequest.getSince());
        analyzeReport.setTill(analyzeRequest.getSince());
        return analyzeReportService.save(analyzeReport);

    }
}
