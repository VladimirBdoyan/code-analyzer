package com.example.analyzedeveloper.controller;

import com.example.analyzedeveloper.dto.AnalyzeRequestDTO;
import com.example.analyzedeveloper.dto.AnalyzeStateDTO;
import com.example.analyzedeveloper.dto.JobDTO;
import com.example.analyzedeveloper.dto.mapper.AnalyzeStateMapper;
import com.example.analyzedeveloper.service.AnalyzeService;
import com.example.analyzedeveloper.service.AnalyzeStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/analyzer")
@RequiredArgsConstructor
public class AnalyzeDeveloperController {

    private final AnalyzeService analyzeService;
    private final AnalyzeStateService analyzeStateService;

    @PostMapping
    public ResponseEntity<JobDTO> analyze(@RequestBody AnalyzeRequestDTO analyzeRequest) {
        UUID uuid = UUID.randomUUID();
        JobDTO jobDTO = new JobDTO(uuid,"In Progress");
        analyzeService.analyze(uuid, analyzeRequest);
        return ResponseEntity
                .ok()
                .body(jobDTO);
    }
    @GetMapping("/status")
    public ResponseEntity<AnalyzeStateDTO> get(@RequestParam UUID jobId) {

        return ResponseEntity.ok().body(analyzeStateService.get(jobId));
    }
}
