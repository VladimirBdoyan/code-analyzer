package com.example.codeanalyzerservice.exception;

public class RepositoryNotFoundException extends CodeAnalyzerRuntimeException {

    public RepositoryNotFoundException(String message) {
        super(message);
    }

    public RepositoryNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
