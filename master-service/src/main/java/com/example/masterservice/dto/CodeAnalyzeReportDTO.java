package com.example.masterservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CodeAnalyzeReportDTO {
    private Long id;
    private String username;
    private Date startDate;
    private Date endDate;
    private CodeAnalyzeResultDTO analyzeResult;
}
