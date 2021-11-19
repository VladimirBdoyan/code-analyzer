package com.example.masterservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyzeReportDTO {
    private Long id;
    private String developerName;
    private Double commitDensity;
    private Double developerCommitDensity;
    private Double pullRequestDensity;
    private Double pullRequestCommitDensity;
    private Integer conversationCount;
    private Integer pullRequestCommitCommentCount;
    private Integer codingRate;
    private List<String> bugs;
}
