package com.example.findbugs.dto;

import com.example.findbugs.entity.enums.CodeSmellCategory;
import lombok.Data;

@Data
public class CodeSmellDTO {
    private Long id;
    private CodeSmellCategory category;
    private String message;
    private String fileName;
}
