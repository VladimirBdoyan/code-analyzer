package com.example.codeanalyzerservice.dto;

import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import lombok.Data;

@Data
public class CodeSmellDTO {
    private Long id;
    private CodeSmellCategory category;
    private String message;
    private String fileName;
}
