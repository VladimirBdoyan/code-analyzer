package com.example.masterservice.dto.mapper;

import com.example.masterservice.dto.AnalyzeStateDTO;
import com.example.masterservice.entity.AnalyzeState;

public final class AnalyzeStateMapper {

    private AnalyzeStateMapper(){
    }

    public static AnalyzeStateDTO mapToDTO(AnalyzeState analyzeState) {
        if (analyzeState==null){
            return null;
        }

        AnalyzeStateDTO rv=new AnalyzeStateDTO();
        rv.setAnalyzeReport(AnalyzeReportMapper.mapToDto(analyzeState.getAnalyzeReport()));
        rv.setFailReason(analyzeState.getFailReason());
        rv.setStatus(analyzeState.getStatus());
        rv.setStartDate(analyzeState.getStartDate());
        rv.setFinishDate(analyzeState.getFinishDate());
        rv.setJobId(analyzeState.getJobId());
        return rv;
    }
}
