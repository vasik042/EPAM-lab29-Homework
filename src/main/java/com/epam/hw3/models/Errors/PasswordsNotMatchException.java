package com.epam.hw3.models.Errors;

public class PasswordsNotMatchException extends RuntimeException{
    public PasswordsNotMatchException() {
        super("Passwords not match");
    }
}
