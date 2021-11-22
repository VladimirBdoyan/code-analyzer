package com.example.codeanalyzerservice.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Data
public class AnalyzeReportDTO {
    private Long id;
    private String username;
    private Date date;
    private Date since;
    private Date till;
    private AnalyzeResultDTO analyzeResults;
}
