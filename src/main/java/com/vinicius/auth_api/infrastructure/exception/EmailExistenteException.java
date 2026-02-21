package com.vinicius.auth_api.infrastructure.exception;

public class EmailExistenteException extends RuntimeException {
    public EmailExistenteException(String message) {
        super(message);
    }

    public EmailExistenteException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
