package com.unideptsystem.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handleApiException(ApiException ex) {
        ApiError err = new ApiError(ex.getStatus().value(), ex.getStatus().getReasonPhrase(), ex.getMessage());
        err.setCode(ex.getCode());
        return new ResponseEntity<>(err, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "Validation failed");
        Map<String,String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, fe -> fe.getDefaultMessage(), (a, b) -> a));
        Map<String,Object> details = new LinkedHashMap<>();
        details.put("fieldErrors", fieldErrors);
        err.setDetails(details);
        err.setCode("VALIDATION_ERROR");
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        ApiError err = new ApiError(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(), ex.getMessage());
        Set<String> allowed = Optional.ofNullable(ex.getSupportedHttpMethods())
                .map(set -> set.stream().map(HttpMethod::name).collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
        Map<String,Object> details = new LinkedHashMap<>();
        details.put("allowedMethods", allowed);
        err.setDetails(details);
        err.setCode("METHOD_NOT_ALLOWED");
        HttpHeaders headers = new HttpHeaders();
        if (!allowed.isEmpty()) headers.setAllow(ex.getSupportedHttpMethods());
        return new ResponseEntity<>(err, headers, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String msg = String.format("Invalid value for parameter '%s': %s", ex.getName(), ex.getValue());
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), msg);
        err.setCode("INVALID_PARAM");
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException ex) {
        String msg = "Database error: constraint violation or invalid data.";
        if (ex.getMostSpecificCause() != null) {
            String cause = ex.getMostSpecificCause().getMessage();
            msg = msg + " (" + (cause.length() > 200 ? cause.substring(0,200) + "..." : cause) + ")";
        }
        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), msg);
        err.setCode("DATA_INTEGRITY_VIOLATION");
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAny(Exception ex) {
        ApiError err = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "An unexpected error occurred.");
        err.setCode("INTERNAL_ERROR");
        // optionally include a short message in details for debugging in non-prod
        Map<String,Object> details = new LinkedHashMap<>();
        details.put("exception", ex.getClass().getSimpleName());
        details.put("message", ex.getMessage());
        err.setDetails(details);
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleJsonParse(HttpMessageNotReadableException ex) {
        String friendly = "Malformed JSON request";
        Map<String, Object> details = new LinkedHashMap<>();
        Throwable cause = ex.getMostSpecificCause();

        if (cause instanceof com.fasterxml.jackson.core.JsonParseException jpe) {
            friendly = "JSON parse error: " + jpe.getOriginalMessage();
            details.put("type", "JsonParseException");
            details.put("location", jpe.getLocation() != null ? jpe.getLocation().toString() : null);
        } else if (cause instanceof com.fasterxml.jackson.databind.exc.MismatchedInputException mie) {
            friendly = "JSON mapping error: " + mie.getMessage();
            details.put("type", "MismatchedInputException");
            details.put("path", mie.getPathReference());
        } else if (cause != null) {
            friendly = "Malformed JSON: " + cause.getMessage();
            details.put("type", cause.getClass().getSimpleName());
        } else {
            friendly = ex.getMessage();
        }

        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                friendly);
        err.setCode("MALFORMED_JSON");
        err.setDetails(details);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}
