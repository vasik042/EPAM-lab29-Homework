package com.epam.hw3.service;

import com.epam.hw3.controllers.assemblers.AuthorAssembler;
import com.epam.hw3.controllers.models.AuthorModel;
import com.epam.hw3.models.Author;
import com.epam.hw3.repositories.AuthorRepository;
import com.epam.hw3.services.imp.AuthorServiceImp;
import com.epam.hw3.util.DataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceImpTest {

    @InjectMocks
    private AuthorServiceImp authorServiceImp;

    @Mock
    private AuthorRepository authorRepository;
    @Spy
    private AuthorAssembler authorAssembler;

    @Test
    void findAuthorTest() {
        Author author = DataUtil.createAuthor();
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        AuthorModel authorModel = authorServiceImp.findAuthor(1);

        assertThat(authorModel.getAuthorDTO(), allOf(
                hasProperty("firstName", equalTo(author.getFirstName())),
                hasProperty("lastName", equalTo(author.getLastName())),
                hasProperty("bio", equalTo(author.getBio()))));
    }

    @Test
    void findAuthorNotFoundTest() {
        when(authorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> authorServiceImp.findAuthor(1));
    }
}
