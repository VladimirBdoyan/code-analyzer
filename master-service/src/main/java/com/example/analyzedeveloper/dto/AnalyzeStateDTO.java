package com.example.analyzedeveloper.dto;

import com.example.analyzedeveloper.entity.enums.ReportStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyzeStateDTO {
    private UUID jobId;
    private ReportStatus status;
    private Date startDate;
    private Date finishDate;
    private String failReason;
    private AnalyzeReportDTO analyzeReport;
}
