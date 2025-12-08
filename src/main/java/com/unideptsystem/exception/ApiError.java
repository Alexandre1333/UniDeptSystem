package com.unideptsystem.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Setter
@Getter
public class ApiError {
    private Instant timestamp = Instant.now();
    private int status;
    private String error;
    private String message;
    private String code;
    private Map<String,Object> details;

    public ApiError(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
