package com.example.codeanalyzerservice.exception.handler;

import lombok.Data;

@Data
public class ValidationError {
    private final String field;
    private final String message;
}
