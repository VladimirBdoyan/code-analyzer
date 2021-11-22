package com.example.masterservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class CodeAnalyzeResultDTO {
    private Long id;
    private int currentRate;
    private int maxRate;
    List<CodeSmellDTO> codeSmells;
}
