package com.example.masterservice.dto.mapper;

import com.example.masterservice.dto.AnalyzeStateDTO;
import com.example.masterservice.entity.AnalyzeState;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class AnalyzeStateMapper {

    public static AnalyzeStateDTO mapToDTO(AnalyzeState entity) {
        if (entity == null){
            return null;
        }

        AnalyzeStateDTO rv=new AnalyzeStateDTO();
        rv.setJobId(entity.getJobId());
        rv.setAnalyzeReport(AnalyzeReportMapper.mapToDto(entity.getAnalyzeReport()));
        rv.setFailReason(entity.getFailReason());
        rv.setStatus(entity.getStatus());
        rv.setStartDate(entity.getStartDate());
        rv.setFinishDate(entity.getFinishDate());

        return rv;
    }
}
