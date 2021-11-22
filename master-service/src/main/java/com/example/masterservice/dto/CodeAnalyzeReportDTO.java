package com.example.masterservice.dto;

import lombok.Data;

import java.util.Date;
@Data
public class CodeAnalyzeReportDTO {
    private Long id;
    private String username;
    private Date since;
    private Date till;
    private CodeAnalyzeResultDTO analyzeResults;
}
