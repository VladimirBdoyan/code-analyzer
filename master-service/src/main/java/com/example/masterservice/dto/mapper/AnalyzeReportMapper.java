package com.example.masterservice.dto.mapper;

import com.example.masterservice.dto.AnalyzeReportDTO;
import com.example.masterservice.dto.StatisticsReportDTO;
import com.example.masterservice.dto.CodeAnalyzeReportDTO;
import com.example.masterservice.dto.CodeSmellDTO;
import com.example.masterservice.entity.AnalyzeReport;
import com.example.masterservice.entity.CodeSmell;
import com.example.masterservice.entity.Developer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class AnalyzeReportMapper {

    private AnalyzeReportMapper() {

    }

    public static AnalyzeReportDTO mapToDto(AnalyzeReport analyzeReport) {
        if (analyzeReport == null) {
            return null;
        }

        AnalyzeReportDTO rv = new AnalyzeReportDTO();
        rv.setDeveloperName(analyzeReport.getDeveloper().getUsername());
        rv.setCommitDensity(analyzeReport.getDeveloperCommitDensity());
        rv.setId(analyzeReport.getId());
        rv.setConversationCount(analyzeReport.getConversationCount());
        rv.setPullRequestDensity(analyzeReport.getPullRequestDensity());
        rv.setPullRequestCommitCommentCount(analyzeReport.getPullRequestCommitCommentCount());
        rv.setPullRequestCommitDensity(analyzeReport.getPullRequestCommitDensity());
        rv.setDeveloperCommitDensity(analyzeReport.getDeveloperCommitDensity());
        rv.setCodingRate(analyzeReport.getCodingRate());
        rv.setCodeSmells(analyzeReport.getCodeSmells().stream().map(CodeSmell::getMessage).collect(Collectors.toList()));
        return rv;
    }

    public static AnalyzeReport mapToEntity(CodeAnalyzeReportDTO codeDTO, StatisticsReportDTO statisticsDto) {
        AnalyzeReport rv = new AnalyzeReport();
        rv.setDeveloper(new Developer(statisticsDto.getUserName()));
        rv.setDeveloperCommitDensity(statisticsDto.getCommitDensityReport().getDensity());
        rv.setPullRequestDensity(statisticsDto.getPullRequestReport().getPullRequestDensity());
        rv.setPullRequestCommitDensity(statisticsDto.getPullRequestReport().getPullRequestCommitDensity());
        rv.setConversationCount(statisticsDto.getPullRequestReport().getConversationCount());
        rv.setPullRequestCommitCommentCount(statisticsDto.getPullRequestReport().getPullRequestCommitCommentCount());
        rv.setCodingRate(codeDTO.getAnalyzeResults().getCurrentRate() / codeDTO.getAnalyzeResults().getMaxRate() * 100);
        List<CodeSmell> codeSmellList = new ArrayList<>();
        for(CodeSmellDTO codeSmellDTO:codeDTO.getAnalyzeResults().getCodeSmells()){
            codeSmellList.add(new CodeSmell(codeSmellDTO.getMessage()));
        }
        rv.setCodeSmells(codeSmellList);

        return rv;
    }

}
