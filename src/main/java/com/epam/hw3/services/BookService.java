package com.epam.hw3.services;

import com.epam.hw3.DTOs.BookDTO;
import com.epam.hw3.controllers.models.BookModel;
import java.util.List;

public interface BookService {
    BookModel findBook(int id);

    List<BookModel> findAllBooks();

    BookModel createBook(BookDTO bookDTO);

    BookModel updateBook(BookDTO bookDTO);

    void deleteBook(int id);
}
