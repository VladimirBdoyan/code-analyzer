package com.example.codeanalyzerservice.dto.mapper;

import com.example.codeanalyzerservice.dto.AnalyzeReportDTO;
import com.example.codeanalyzerservice.entity.AnalyzeReport;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class AnalyzeReportMapper {

    public static AnalyzeReportDTO mapToDTO(AnalyzeReport entity) {
        if (entity == null){
            return null;
        }

        AnalyzeReportDTO dto = new AnalyzeReportDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setAnalyzeResult(AnalyzeResultMapper.mapToDTO(entity.getAnalyzeResult()));
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}
