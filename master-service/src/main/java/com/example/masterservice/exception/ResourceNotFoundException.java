package com.example.masterservice.exception;

public class ResourceNotFoundException extends MasterRuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
