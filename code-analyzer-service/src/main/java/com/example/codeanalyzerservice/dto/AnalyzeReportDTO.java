package com.example.findbugs.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AnalyzeReportDTO {
    private Long id;
    private String username;
    private Date date;
    private AnalyzeResultDTO analyzeResults;
}
