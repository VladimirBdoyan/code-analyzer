package com.example.codeanalyzerservice.exception;

public class RepositoryNotFoundException extends AppRuntimeException {

    public RepositoryNotFoundException(String message) {
        super(message);
    }

    public RepositoryNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
