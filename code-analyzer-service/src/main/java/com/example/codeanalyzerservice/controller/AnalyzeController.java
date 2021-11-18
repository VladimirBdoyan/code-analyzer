package com.example.findbugs.controller;

import com.example.findbugs.dto.AnalyzeRequestDTO;
import com.example.findbugs.entity.AnalyzeReport;
import com.example.findbugs.service.AnalyzeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analyze")
@RequiredArgsConstructor
public class AnalyzeController {

    private final AnalyzeService analyzerService;

    @PostMapping
    public ResponseEntity<AnalyzeReport> analyze(@RequestBody AnalyzeRequestDTO analyzeRequest)  {
        AnalyzeReport analyzeReport = analyzerService.analyze(analyzeRequest);
        return ResponseEntity
                .ok()
                .body(analyzeReport);
    }
}
