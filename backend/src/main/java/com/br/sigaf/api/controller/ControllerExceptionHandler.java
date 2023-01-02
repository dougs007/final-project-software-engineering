package com.br.sigaf.api.controller;

import com.br.sigaf.domain.exception.ResourceNotFoundException;
import com.br.sigaf.api.controller.exceptions.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error("Resource not found")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(status).body(err);
    }
}
