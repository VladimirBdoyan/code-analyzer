package com.example.masterservice.dto.codeAnalyzerResponse;

import lombok.Data;

import java.util.List;
@Data
public class AnalyzeServiceResultDTO {
    private Long id;
    private int currentRate;
    private int maxRate;
    List<CodeSmellDTO> codeSmells;
}
