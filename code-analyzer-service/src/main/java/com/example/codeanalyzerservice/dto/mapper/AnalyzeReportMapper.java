package com.example.codeanalyzerservice.dto.mapper;

import com.example.codeanalyzerservice.dto.AnalyzeReportDTO;
import com.example.codeanalyzerservice.entity.AnalyzeReport;

public final class AnalyzeReportMapper {

    private AnalyzeReportMapper() {

    }

    public static AnalyzeReportDTO mapToDTO(AnalyzeReport analyzeReport) {
        if (analyzeReport == null){
            return null;
        }

        AnalyzeReportDTO rv=new AnalyzeReportDTO();
        rv.setId(analyzeReport.getId());
        rv.setAnalyzeResults(AnalyzeResultMapper.mapToDTO(analyzeReport.getAnalyzeResult()));
        rv.setDate(analyzeReport.getDate());
        rv.setSince(analyzeReport.getSince());
        rv.setTill(analyzeReport.getTill());
        rv.setUsername(analyzeReport.getUsername());
        return rv;
    }
}
