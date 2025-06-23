package com.example.ms_users_java.shared.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // Handle custom AppException globally
    @ExceptionHandler(AppException.class)
    public ResponseEntity<Map<String, Object>> handleAppException(AppException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", ex.getMessage());
        response.put("errorCode", ex.getHttpStatus());

        // Log the error details if available
        handleErrorLogging(ex.getDetails());

        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    // Handle other exceptions globally
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", "An unexpected error occurred");
        response.put("errorCode", HttpStatus.INTERNAL_SERVER_ERROR);

        // Log the error details if available
        handleErrorLogging(ex);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void handleErrorLogging(Throwable details) {
        if (details != null) {
            // Set log object response message
            Map<String, Object> response = new HashMap<>();

            response.put("location", details.getStackTrace()[0].toString());
            response.put("errorType", details.getClass().getSimpleName());
            response.put("errorMessage", details.getMessage());

            log.error("Error occurred at {}: {} - {}", response.get("location"), response.get("errorType"), response.get("errorMessage"));
        } else {
            log.error("An error occurred, but no details were provided.");
        }
    }
}
