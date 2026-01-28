package com.example.demo.exception;

public class AuthException extends RuntimeException {
    private String message;
    private boolean status;

    public AuthException(String message, boolean status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
