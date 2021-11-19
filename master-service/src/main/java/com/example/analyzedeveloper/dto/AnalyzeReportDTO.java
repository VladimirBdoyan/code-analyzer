package com.example.analyzedeveloper.dto;

import com.example.analyzedeveloper.entity.Developer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyzeReportDTO {
    private Long id;
    private Developer developer;
    private Double commitDensity;
    private Double developerCommitDensity;
    private Double pullRequestDensity;
    private Double pullRequestCommitDensity;
    private Integer conversationCount;
    private Integer pullRequestCommitCommentCount;
    private Integer codingRate;
    private List<String> bugs;
}
