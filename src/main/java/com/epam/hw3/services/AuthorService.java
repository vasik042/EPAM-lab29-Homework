package com.epam.hw3.services;

import com.epam.hw3.DTOs.AuthorDTO;
import com.epam.hw3.controllers.models.AuthorModel;
import java.util.List;

public interface AuthorService {
    AuthorModel findAuthor(int id);

    List<AuthorModel> findAll();

    AuthorModel createAuthor(AuthorDTO authorDTO);

    AuthorModel updateAuthor(AuthorDTO authorDTO);

    void deleteAuthor(int id);
}
