package com.epam.hw3.services;

import com.epam.hw3.DTOs.BookDTO;
import com.epam.hw3.controllers.models.BookModel;

public interface BookService {
    BookModel findBook(int id);

    BookModel createBook(BookDTO bookDTO);

    BookModel updateBook(BookDTO bookDTO);

    void deleteBook(int id);
}
