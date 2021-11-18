package com.example.findbugs.exception;

public class RepositoriesNotFoundException extends AppRuntimeException {
    public RepositoriesNotFoundException(String message) {
        super(message);
    }

    public RepositoriesNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}
