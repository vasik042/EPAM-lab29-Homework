package com.epam.hw3.services.imp;

import com.epam.hw3.DTOs.AuthorDTO;
import com.epam.hw3.controllers.assemblers.AuthorAssembler;
import com.epam.hw3.controllers.models.AuthorModel;
import com.epam.hw3.models.Author;
import com.epam.hw3.repositories.AuthorRepository;
import com.epam.hw3.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImp implements AuthorService {
    AuthorRepository authorRepository;
    AuthorAssembler authorAssembler;

    @Autowired
    public AuthorServiceImp(AuthorRepository authorRepository, AuthorAssembler authorAssembler) {
        this.authorRepository = authorRepository;
        this.authorAssembler = authorAssembler;
    }

    @Override
    public AuthorModel findAuthor(int id) {
        return authorAssembler.toModel(authorRepository.findById(id).get().toDTO());
    }

    @Override
    public AuthorModel createAuthor(AuthorDTO authorDTO) {
        Author author = authorRepository.save(new Author(authorDTO.firstName, authorDTO.lastName, authorDTO.bio));
        return authorAssembler.toModel(author.toDTO());
    }

    @Override
    public AuthorModel updateAuthor(AuthorDTO authorDTO) {
        authorRepository.update(authorDTO.bio, authorDTO.firstName, authorDTO.lastName, authorDTO.id);
        return authorAssembler.toModel(authorDTO);
    }

    @Override
    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }
}