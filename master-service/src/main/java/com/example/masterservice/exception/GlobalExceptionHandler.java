package com.example.masterservice.exception;

import com.example.masterservice.exception.model.ErrorEntity;
import com.example.masterservice.exception.model.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorEntity> handleResourceNotFoundException(
            Exception exp) {
        ErrorEntity error =new ErrorEntity(new Date(), HttpStatus.NOT_FOUND.value(),
                exp.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorEntity> handleConstraintViolationException(
            ConstraintViolationException exp) {
        ErrorEntity error = new ErrorEntity(new Date(), HttpStatus.BAD_REQUEST.value(),
                "Constraint violation:");

        Set<ValidationError> constraintViolations = new LinkedHashSet<>();

        exp.getConstraintViolations()
                .forEach(constraintViolation -> {
                    constraintViolations.add(new ValidationError(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getMessage()));
                });

        error.setErrors(constraintViolations);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler({AppRuntimeException.class, Exception.class})
    public ResponseEntity<ErrorEntity> handleAppRuntimeException(
            Exception exp) {
        ErrorEntity error =  new ErrorEntity(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exp.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
