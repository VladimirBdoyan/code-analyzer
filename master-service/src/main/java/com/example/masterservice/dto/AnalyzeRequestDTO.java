package com.example.masterservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AnalyzeRequestDTO {

    @NotNull(message = "Login of developer is mandatory")
    private String login;

    private String organization;
    private String repoName;

    @NotNull(message = "Start date is mandatory")
    private Date startDate;

    @NotNull(message = "End date is mandatory")
    private Date endDate;
}
