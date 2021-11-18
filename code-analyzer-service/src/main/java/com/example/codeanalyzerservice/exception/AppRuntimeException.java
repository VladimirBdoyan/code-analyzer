package com.example.codeanalyzerservice.exception;

public class AppRuntimeException extends RuntimeException {

    public AppRuntimeException(String message) {
        super(message);
    }

    public AppRuntimeException(String message, Exception cause) {
        super(message, cause);
    }
}