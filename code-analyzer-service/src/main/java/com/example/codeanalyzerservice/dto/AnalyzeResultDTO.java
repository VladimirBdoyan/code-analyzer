package com.example.codeanalyzerservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnalyzeResultDTO {
    private Long id;
    private int currentRate;
    private int maxRate;
    List<CodeSmellDTO> codeSmells;
}
