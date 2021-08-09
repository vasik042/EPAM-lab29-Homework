package com.epam.hw3.models.Errors;

import java.time.LocalDateTime;

public class CustomError{

    String message;
    String errorType;
    LocalDateTime time;

    public CustomError(String message, String errorType) {
        this.message = message;
        this.errorType = errorType;
        this.time = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
