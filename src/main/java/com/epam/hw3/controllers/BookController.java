package com.epam.hw3.controllers;

import com.epam.hw3.DTOs.BookDTO;
import com.epam.hw3.api.BookAPI;
import com.epam.hw3.controllers.models.BookModel;
import com.epam.hw3.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController implements BookAPI {
    BookService bookService;
    Logger logger;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
        this.logger = LoggerFactory.getLogger(BookController.class);
    }

    @Override
    public BookModel createBook(BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }

    @Override
    public BookModel getBook(int id) {
        return bookService.findBook(id);
    }

    @Override
    public List<BookModel> getAllBook() {
        return bookService.findAllBooks();
    }

    @Override
    public BookModel updateBook(int id, BookDTO bookDTO) {
        bookDTO.setId(id);
        return bookService.updateBook(bookDTO);
    }

    @Override
    public ResponseEntity<Void> deleteBook(int id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
