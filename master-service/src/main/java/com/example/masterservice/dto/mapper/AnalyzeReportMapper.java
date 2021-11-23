package com.example.masterservice.dto.mapper;

import com.example.masterservice.dto.AnalyzeReportDTO;
import com.example.masterservice.dto.StatisticsReportDTO;
import com.example.masterservice.dto.CodeAnalyzeReportDTO;
import com.example.masterservice.dto.CodeSmellDTO;
import com.example.masterservice.entity.AnalyzeReport;
import com.example.masterservice.entity.CodeSmell;
import com.example.masterservice.entity.Developer;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public final class AnalyzeReportMapper {

    public static AnalyzeReportDTO mapToDto(AnalyzeReport entity) {
        if (entity == null) {
            return null;
        }

        AnalyzeReportDTO dto = new AnalyzeReportDTO();
        dto.setId(entity.getId());
        dto.setDeveloper(entity.getDeveloper().getUsername());
        dto.setCommitDensity(entity.getDeveloperCommitDensity());
        dto.setConversationCount(entity.getConversationCount());
        dto.setPullRequestDensity(entity.getPullRequestDensity());
        dto.setPullRequestCommitCommentCount(entity.getPullRequestCommitCommentCount());
        dto.setPullRequestCommitDensity(entity.getPullRequestCommitDensity());
        dto.setDeveloperCommitDensity(entity.getDeveloperCommitDensity());
        dto.setCodingRate(entity.getCodingRate());
        dto.setCodeSmells(entity.getCodeSmells().stream()
                .map(CodeSmell::getMessage)
                .collect(Collectors.toList()));

        return dto;
    }

    public static AnalyzeReport mapToEntity(CodeAnalyzeReportDTO codeDTO, StatisticsReportDTO statisticsDto) {
        AnalyzeReport rv = new AnalyzeReport();
        rv.setDeveloper(new Developer(statisticsDto.getUserName()));
        rv.setDeveloperCommitDensity(statisticsDto.getCommitDensity());
        rv.setPullRequestDensity(statisticsDto.getPullRequestReport().getPullRequestDensity());
        rv.setPullRequestCommitDensity(statisticsDto.getPullRequestReport().getPullRequestCommitDensity());
        rv.setConversationCount(statisticsDto.getPullRequestReport().getConversationCount());
        rv.setPullRequestCommitCommentCount(statisticsDto.getPullRequestReport().getPullRequestCommitCommentCount());
        rv.setCodingRate(codeDTO.getAnalyzeResult().getCurrentRate() / codeDTO.getAnalyzeResult().getMaxRate() * 100);
        List<CodeSmell> codeSmellList = new ArrayList<>();
        for(CodeSmellDTO codeSmellDTO:codeDTO.getAnalyzeResult().getCodeSmells()){
            codeSmellList.add(new CodeSmell(codeSmellDTO.getMessage()));
        }
        rv.setCodeSmells(codeSmellList);

        return rv;
    }

}
