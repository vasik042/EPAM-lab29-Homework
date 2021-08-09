package com.epam.hw3.controllers;

import com.epam.hw3.DTOs.AuthorDTO;
import com.epam.hw3.api.AuthorAPI;
import com.epam.hw3.controllers.models.AuthorModel;
import com.epam.hw3.services.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController implements AuthorAPI {

    AuthorService authorService;
    Logger logger;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
        this.logger = LoggerFactory.getLogger(BookController.class);
    }

    @Override
    public AuthorModel createAuthor(AuthorDTO authorDTO) {
        return authorService.createAuthor(authorDTO);
    }

    @Override
    public AuthorModel getAuthor(int id) {
        return authorService.findAuthor(id);
    }

    @Override
    public AuthorModel updateAuthor(int id, AuthorDTO authorDTO) {
        authorDTO.setId(id);
        return authorService.updateAuthor(authorDTO);
    }

    @Override
    public ResponseEntity<Void> deleteAuthor(int id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
