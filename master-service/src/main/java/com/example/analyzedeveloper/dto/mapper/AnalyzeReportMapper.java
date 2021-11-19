package com.example.analyzedeveloper.dto.mapper;

import com.example.analyzedeveloper.dto.AnalyzeReportDTO;
import com.example.analyzedeveloper.dto.AnalyzeStateDTO;
import com.example.analyzedeveloper.entity.AnalyzeReport;
import com.example.analyzedeveloper.entity.BugEntity;

import java.util.function.Function;
import java.util.stream.Collectors;

public final class AnalyzeReportMapper {
    private AnalyzeReportMapper(){}

    public static AnalyzeReportDTO mapToDto(AnalyzeReport analyzeReport){
        if (analyzeReport==null){
            return null;
        }
        AnalyzeReportDTO rv=new AnalyzeReportDTO();
        rv.setBugs(analyzeReport.getBugs().stream().map((Function<BugEntity, String>) BugEntity::getMessage).collect(Collectors.toList()));
        rv.setCodingRate(analyzeReport.getCodingRate());
        rv.setDeveloper(analyzeReport.getDeveloper());
        rv.setCommitDensity(analyzeReport.getDeveloperCommitDensity());
        rv.setId(analyzeReport.getId());
        rv.setConversationCount(analyzeReport.getConversationCount());
        rv.setPullRequestDensity(analyzeReport.getPullRequestDensity());
        rv.setPullRequestCommitCommentCount(analyzeReport.getPullRequestCommitCommentCount());
        rv.setPullRequestCommitDensity(analyzeReport.getPullRequestCommitDensity());
        rv.setDeveloperCommitDensity(analyzeReport.getDeveloperCommitDensity());

        return rv;
    }

}
