package com.epam.hw3.controllers;

import com.epam.hw3.models.Errors.CustomError;
import com.epam.hw3.models.Errors.PasswordsNotMatchException;
import com.epam.hw3.models.Errors.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {
    Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<CustomError> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException ex) {
        logger.error("methodArgumentNotValidExceptionHandle: message {}", ex.getMessage(), ex);
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(e -> new CustomError(e.getDefaultMessage(), "Validation error"))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PasswordsNotMatchException.class)
    public CustomError PasswordsNotMatchExceptionHandle(PasswordsNotMatchException ex) {
        logger.error("PasswordsNotMatchExceptionHandle: message {}", ex.getMessage(), ex);
        return new CustomError(ex.getMessage(), "Validation error");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public CustomError UserNotFoundExceptionHandle(UserNotFoundException ex) {
        logger.error("UserNotFoundExceptionHandle: message {}", ex.getMessage(), ex);
        return new CustomError(ex.getMessage(), "Bad request");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public CustomError ExceptionHandle(Exception ex) {
        logger.error("ExceptionHandle: message {}", ex.getMessage(), ex);
        return new CustomError(ex.getMessage(), "Internal server error");
    }
}
