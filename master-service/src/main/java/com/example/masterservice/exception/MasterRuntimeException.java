package com.example.masterservice.exception;

public class MasterRuntimeException extends RuntimeException {

    public MasterRuntimeException(String message) {
        super(message);
    }

    public MasterRuntimeException(String message, Exception cause) {
        super(message, cause);
    }
}
