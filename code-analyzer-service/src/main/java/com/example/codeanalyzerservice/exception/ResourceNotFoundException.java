package com.example.codeanalyzerservice.exception;

public class ResourceNotFoundException extends AppRuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
