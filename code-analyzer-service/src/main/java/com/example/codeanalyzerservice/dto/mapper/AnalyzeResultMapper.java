package com.example.codeanalyzerservice.dto.mapper;

import com.example.codeanalyzerservice.dto.AnalyzeResultDTO;
import com.example.codeanalyzerservice.entity.AnalyzeResult;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class AnalyzeResultMapper {

    public static AnalyzeResultDTO mapToDTO(AnalyzeResult entity) {
        if (entity == null){
            return null;
        }

        AnalyzeResultDTO dto  = new AnalyzeResultDTO();
        dto.setId(entity.getId());
        dto.setMaxRate(dto.getMaxRate());
        dto.setCurrentRate(dto.getCurrentRate());
        dto.setCodeSmells(CodeSmellMapper.mapToListDTO(entity.getCodeSmells()));
        return dto;
    }
}
