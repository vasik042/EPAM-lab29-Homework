package com.epam.hw3.DTOs;

public class UserDTO {
    public String firstName;
    public String lastName;
    public String username;
    public String email;
    public String password;
    public String repeatPassword;

    public UserDTO(String firstName, String lastName, String username, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }
}
