package com.vk.segmentation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    static class ErrorResponse {
        private LocalDateTime timestamp;
        private String message;
        private String path;

        public ErrorResponse(String message, WebRequest request) {
            this.timestamp = LocalDateTime.now();
            this.message = message;
            this.path = request.getDescription(false).replace("uri=", "");
        }

        // Getters
        public LocalDateTime getTimestamp() { return timestamp; }
        public String getMessage() { return message; }
        public String getPath() { return path; }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), request);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
