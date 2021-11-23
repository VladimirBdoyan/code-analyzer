package com.example.masterservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AnalyzeRequestDTO {

    @NotNull(message = "Username of developer is mandatory")
    private String username;

    private String organization;

    private String repoName;

    @NotNull(message = "Analyze start_date(from) is mandatory")
    private Date startDate;

    @NotNull(message = "Analyze end_date(to) is mandatory")
    private Date endDate;
}
