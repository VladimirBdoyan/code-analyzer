package com.example.codeanalyzerservice.exception;

public class ResourceNotFoundException extends CodeAnalyzerRuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
