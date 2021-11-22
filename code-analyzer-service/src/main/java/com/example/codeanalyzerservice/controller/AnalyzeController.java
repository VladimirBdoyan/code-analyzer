package com.example.codeanalyzerservice.controller;

import com.example.codeanalyzerservice.dto.AnalyzeReportDTO;
import com.example.codeanalyzerservice.dto.AnalyzeRequestDTO;
import com.example.codeanalyzerservice.service.AnalyzeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/analyze")
@RequiredArgsConstructor
public class AnalyzeController {

    private final AnalyzeService analyzerService;

    @PostMapping
    public ResponseEntity<AnalyzeReportDTO> analyze(@RequestBody @Valid
                                                                AnalyzeRequestDTO analyzeRequest)  {
        AnalyzeReportDTO analyzeReport = analyzerService.analyze(analyzeRequest);
        return ResponseEntity
                .ok()
                .body(analyzeReport);
    }
}
