package com.example.codeanalyzerservice.dto.mapper;

import com.example.codeanalyzerservice.dto.AnalyzeResultDTO;
import com.example.codeanalyzerservice.entity.AnalyzeResult;

public final class AnalyzeResultMapper {

    private AnalyzeResultMapper() {

    }

    public static AnalyzeResultDTO mapToDTO(AnalyzeResult result) {
        if (result==null){
            return null;
        }

        AnalyzeResultDTO rv = new AnalyzeResultDTO();
        rv.setId(result.getId());
        rv.setMaxRate(result.getMaxRate());
        rv.setCurrentRate(result.getCurrentRate());
        rv.setCodeSmells(CodeSmellMapper.mapToListDTO(result.getCodeSmells()));
        return rv;
    }
}
