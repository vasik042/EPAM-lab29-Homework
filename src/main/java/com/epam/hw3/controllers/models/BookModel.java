package com.epam.hw3.controllers.models;

import com.epam.hw3.DTOs.BookDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.RepresentationModel;

public class BookModel extends RepresentationModel<BookModel> {
    @JsonUnwrapped
    private BookDTO bookDTO;

    public BookModel(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public BookModel() {
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }
}