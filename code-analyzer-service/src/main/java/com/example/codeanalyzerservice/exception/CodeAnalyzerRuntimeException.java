package com.example.codeanalyzerservice.exception;

public class CodeAnalyzerRuntimeException extends RuntimeException {

    public CodeAnalyzerRuntimeException(String message) {
        super(message);
    }

    public CodeAnalyzerRuntimeException(String message, Exception cause) {
        super(message, cause);
    }
}
