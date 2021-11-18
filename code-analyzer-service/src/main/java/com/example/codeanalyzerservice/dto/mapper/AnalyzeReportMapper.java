package com.example.findbugs.dto.mapper;

import com.example.findbugs.dto.AnalyzeReportDTO;
import com.example.findbugs.entity.AnalyzeReport;

public final class AnalyzeReportMapper {
    private AnalyzeReportMapper(){}

    public static AnalyzeReportDTO mapToDTO(AnalyzeReport analyzeReport){
        if (analyzeReport==null){
            return null;
        }

        AnalyzeReportDTO rv=new AnalyzeReportDTO();
        rv.setId(analyzeReport.getId());
        rv.setAnalyzeResults(AnalyzeResultMapper.mapToDTO(analyzeReport.getAnalyzeResult()));
        rv.setDate(analyzeReport.getDate());
        rv.setUsername(analyzeReport.getUsername());
        return rv;
    }
}
