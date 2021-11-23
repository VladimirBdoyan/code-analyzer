package com.example.masterservice.exception.handler;

import com.example.masterservice.exception.MasterRuntimeException;
import com.example.masterservice.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        return ErrorResponse
                .builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        Set<ValidationError> constraintViolations = new LinkedHashSet<>();

        e.getConstraintViolations()
                .forEach(constraintViolation -> constraintViolations.add(new ValidationError(
                        constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getMessage())));

        return ErrorResponse
                .builder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .data(constraintViolations)
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MasterRuntimeException.class)
    public ErrorResponse handleMasterRuntimeException(MasterRuntimeException e) {
        return ErrorResponse
                .builder()
                .timestamp(new Date())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .build();
    }
}
