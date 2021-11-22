package com.example.masterservice.dto.codeAnalyzerResponse;

import lombok.Data;

import java.util.Date;
@Data
public class AnalyzeServiceReportDTO {
    private Long id;
    private String username;
    private Date date;
    private Date since;
    private Date till;
    private AnalyzeServiceResultDTO analyzeResults;
}