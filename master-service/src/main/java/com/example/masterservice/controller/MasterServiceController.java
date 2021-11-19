package com.example.masterservice.controller;

import com.example.masterservice.dto.AnalyzeRequestDTO;
import com.example.masterservice.dto.AnalyzeStateDTO;
import com.example.masterservice.dto.JobDTO;
import com.example.masterservice.dto.mapper.AnalyzeStateMapper;
import com.example.masterservice.service.AnalyzeService;
import com.example.masterservice.service.AnalyzeStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/analyzer")
@RequiredArgsConstructor
public class MasterServiceController {

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
