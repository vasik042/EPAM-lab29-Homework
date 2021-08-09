package com.epam.hw3.services;

import com.epam.hw3.DTOs.AuthorDTO;
import com.epam.hw3.controllers.models.AuthorModel;

public interface AuthorService {
    AuthorModel findAuthor(int id);

    AuthorModel createAuthor(AuthorDTO authorDTO);

    AuthorModel updateAuthor(AuthorDTO authorDTO);

    void deleteAuthor(int id);
}
