package com.example.findbugs.exception;

public class ResourceNotFoundException extends AppRuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
