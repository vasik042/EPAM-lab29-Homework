package com.epam.hw3.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@JsonInclude (JsonInclude.Include.NON_NULL)
public class UserDTO {
    @NotBlank(message = "First name should not be empty")
    public String firstName;
    @NotBlank(message = "Last name should not be empty")
    public String lastName;
    @NotBlank(message = "Username should not be empty")
    public String username;
    @NotBlank(message = "Email should not be empty")
    @Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+", message = "Email should be valid (example: someEmail@mail.com)")
    public String email;
    @NotBlank(message = "Password should not be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password should contains at least 8 characters, one uppercase letter and one number")
    public String password;
    @NotBlank(message = "Repeat password should not be empty")
    public String repeatPassword;

    public UserDTO(String firstName, String lastName, String username, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }
}
