package com.example.masterservice.service.model;

import com.example.masterservice.entity.AnalyzeReport;
import com.example.masterservice.entity.AnalyzeState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class Context {
    private String username;
    private AnalyzeState analyzeState;
    private AnalyzeReport analyzeReport;
    private String errorStackTrace;
}
