package com.example.codeanalyzerservice.exception.model;

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
    private Set<ValidationError> errors;

    public void addValidationError(String field, String message){
        if(errors == null){
            errors = new LinkedHashSet<>();
        }
        errors.add(new ValidationError(field, message));
    }
}
