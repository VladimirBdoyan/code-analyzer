package com.example.codeanalyzerservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyzeReportDTO {
    private Long id;
    private String username;
    private AnalyzeResultDTO analyzeResult;
    private Date startDate;
    private Date endDate;
    private Date createdAt;
    private Date updatedAt;
}
