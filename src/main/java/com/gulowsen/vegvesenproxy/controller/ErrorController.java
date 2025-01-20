package com.gulowsen.vegvesenproxy.controller;

import com.gulowsen.vegvesenproxy.errorhandling.APIError;
import com.gulowsen.vegvesenproxy.errorhandling.exception.BadRequestException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.CustomParseException;
import com.gulowsen.vegvesenproxy.errorhandling.exception.ExternalAPIException;
import com.gulowsen.vegvesenproxy.utils.FakeLogger;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Hidden
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler({CustomParseException.class})
    public ResponseEntity<APIError> handleParseException(CustomParseException e) {
        FakeLogger.logError(e);
        return new ResponseEntity<>(
                new APIError(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        e.getMessage()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler({ExternalAPIException.class})
    public ResponseEntity<APIError> handleExternalAPIException(ExternalAPIException e) {
        FakeLogger.logError(e);
        return new ResponseEntity<>(
                new APIError(HttpStatus.INTERNAL_SERVER_ERROR,
                        e.getMessage()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<APIError> handleBadRequestException(BadRequestException e) {
        FakeLogger.logError(e);
        return new ResponseEntity<>(
                new APIError(HttpStatus.BAD_REQUEST,
                        e.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIError> handleValidationExceptions(ConstraintViolationException e) {
        StringBuilder errorBuilder = new StringBuilder();
        e.getConstraintViolations().forEach(constraintViolation ->
                errorBuilder.append("Invalid value (")
                        .append(constraintViolation.getInvalidValue())
                        .append(") : ")
                        .append(constraintViolation.getMessage()));
        return new ResponseEntity<>(
                new APIError(HttpStatus.BAD_REQUEST,
                        errorBuilder.toString()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
