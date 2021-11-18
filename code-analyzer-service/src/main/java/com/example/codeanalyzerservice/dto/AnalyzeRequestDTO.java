package com.example.codeanalyzerservice.dto;

import lombok.Data;
import java.util.Date;

@Data
public class AnalyzeRequestDTO {
    private String login;
    private String organization;
    private String repoName;
    private Date startDate;
    private Date endDate;
}
