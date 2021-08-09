package com.epam.hw3.controllers.models;

import com.epam.hw3.DTOs.AuthorDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.RepresentationModel;

public class AuthorModel extends RepresentationModel<AuthorModel> {
    @JsonUnwrapped
    private AuthorDTO authorDTO;

    public AuthorModel(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }
}
