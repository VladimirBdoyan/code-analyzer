package com.example.codeanalyzerservice.controller;

import com.example.codeanalyzerservice.dto.AnalyzeReportDTO;
import com.example.codeanalyzerservice.dto.AnalyzeRequestDTO;
import com.example.codeanalyzerservice.service.AnalyzeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analyze")
@RequiredArgsConstructor
public class AnalyzeController {

    private final AnalyzeService analyzerService;

    @PostMapping
    public ResponseEntity<AnalyzeReportDTO> analyze(@RequestBody AnalyzeRequestDTO analyzeRequest)  {
        AnalyzeReportDTO analyzeReport = analyzerService.analyze(analyzeRequest);
        return ResponseEntity
                .ok()
                .body(analyzeReport);
    }
}
