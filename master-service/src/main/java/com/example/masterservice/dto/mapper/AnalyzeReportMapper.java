package com.example.masterservice.dto.mapper;

import com.example.masterservice.dto.AnalyzeReportDTO;
import com.example.masterservice.dto.StatisticsReportDto;
import com.example.masterservice.dto.codeAnalyzerResponse.AnalyzeServiceReportDTO;
import com.example.masterservice.dto.codeAnalyzerResponse.CodeSmellDTO;
import com.example.masterservice.entity.AnalyzeReport;
import com.example.masterservice.entity.BugEntity;
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
        rv.setBugs(analyzeReport.getBugs().stream().map(BugEntity::getMessage).collect(Collectors.toList()));
        return rv;
    }

    public static AnalyzeReport mapToEntity(AnalyzeServiceReportDTO codeDTO, StatisticsReportDto statisticsDto) {
        AnalyzeReport rv = new AnalyzeReport();
        rv.setDeveloper(new Developer(statisticsDto.getUserName()));
        rv.setDeveloperCommitDensity(statisticsDto.getCommitDensityReport().getDensity());
        rv.setPullRequestDensity(statisticsDto.getPullRequestReport().getPullRequestDensity());
        rv.setPullRequestCommitDensity(statisticsDto.getPullRequestReport().getPullRequestCommitDensity());
        rv.setConversationCount(statisticsDto.getPullRequestReport().getConversationCount());
        rv.setPullRequestCommitCommentCount(statisticsDto.getPullRequestReport().getPullRequestCommitCommentCount());
        rv.setCodingRate(codeDTO.getAnalyzeResults().getCurrentRate() / codeDTO.getAnalyzeResults().getMaxRate() * 100);
        List<BugEntity> bugEntityList = new ArrayList<>();
        for(CodeSmellDTO codeSmellDTO:codeDTO.getAnalyzeResults().getCodeSmells()){
            bugEntityList.add(new BugEntity(codeSmellDTO.getMessage()));
        }
        rv.setBugs(bugEntityList);

        return rv;
    }

}
