package com.example.masterservice.dto.codeAnalyzerResponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CodeSmellDTO {
    private Long id;
    private String message;
    @JsonIgnore
    private String fileName;
}
