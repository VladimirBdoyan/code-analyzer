package com.example.codeanalyzerservice.dto;

import com.example.codeanalyzerservice.entity.enums.CodeSmellCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CodeSmellDTO {

    private Long id;
    private CodeSmellCategory category;
    private String message;
    @JsonIgnore
    private String fileName;
}
