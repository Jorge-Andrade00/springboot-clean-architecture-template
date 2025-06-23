package com.example.ms_users_java.shared.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final Throwable details;

    public AppException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.details = null;
    }

    public AppException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.details = null;
    }

    public AppException(String message, Throwable details) {
        super(message, details);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.details = details;
    }
}
