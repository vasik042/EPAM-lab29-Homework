package com.epam.hw3.service;

import com.epam.hw3.controllers.assemblers.BookAssembler;
import com.epam.hw3.controllers.models.BookModel;
import com.epam.hw3.models.Book;
import com.epam.hw3.repositories.BookRepository;
import com.epam.hw3.services.imp.BookServiceImp;
import com.epam.hw3.util.DataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class BookServiceImpTest {
    @InjectMocks
    private BookServiceImp bookServiceImp;

    @Mock
    private BookRepository bookRepository;
    @Spy
    private BookAssembler bookAssembler;

    @Test
    void findBookTest() {
        Book book = DataUtil.createBook();
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        BookModel bookModel = bookServiceImp.findBook(1);

        assertThat(bookModel.getBookDTO(), allOf(
                hasProperty("name", equalTo(book.getName())),
                hasProperty("description", equalTo(book.getDescription()))));
    }
}
