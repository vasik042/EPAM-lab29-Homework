package com.epam.hw3.DTOs;

import com.epam.hw3.models.Author;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDTO {
    public int id;
    @NotBlank(message = "First name should not be empty")
    public String firstName;
    @NotBlank(message = "Last name should not be empty")
    public String lastName;
    public String bio;

    public Author toEntity(){
        Author author = new Author(firstName, lastName, bio);
        author.setId(id);
        return author;
    }

    public AuthorDTO(String firstName, String lastName, String bio, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.id = id;
    }

    public AuthorDTO(String firstName, String lastName, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }

    public AuthorDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
