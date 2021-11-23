package com.example.codeanalyzerservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnalyzeResultDTO {
    private Long id;
    private Integer currentRate;
    private Integer maxRate;
    private List<CodeSmellDTO> codeSmells;
}
