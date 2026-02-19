package com.vinicius.auth_api.infrastructure.exception;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String message){ super(message);}
    public InvalidTokenException(String message, Throwable throwable){ super(message,throwable);}
}
