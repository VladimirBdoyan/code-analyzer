package com.example.codeanalyzerservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AnalyzeRequestDTO {

    @NotNull
    private String login;

    private String organization;
    private String repoName;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;
}
