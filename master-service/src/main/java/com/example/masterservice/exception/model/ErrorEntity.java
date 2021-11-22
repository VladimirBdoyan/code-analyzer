package com.example.masterservice.exception.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorEntity {

    private final Date timestamp;
    private final int status;
    private final String message;
    private String stackTrace;
    private Set<ValidationError> errors;

}
