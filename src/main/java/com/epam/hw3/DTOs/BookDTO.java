package com.epam.hw3.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {
    @NotBlank(message = "Name should not be empty")
    public String name;
    public String description;
    @NotNull(message = "Author id should not be empty")
    public int authorId;
    public int id;

    public BookDTO(String name, String description, int authorId, int id) {
        this.name = name;
        this.description = description;
        this.authorId = authorId;
        this.id = id;
    }

    public BookDTO(String name, String description, int authorId) {
        this.name = name;
        this.description = description;
        this.authorId = authorId;
    }

    public BookDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
