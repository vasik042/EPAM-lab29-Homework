package com.epam.hw3.controllers.assemblers;

import com.epam.hw3.DTOs.BookDTO;
import com.epam.hw3.controllers.BookController;
import com.epam.hw3.controllers.models.BookModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BookAssembler extends RepresentationModelAssemblerSupport<BookDTO, BookModel> {

    public BookAssembler() {
        super(BookController.class, BookModel.class);
    }

    @Override
    public BookModel toModel(BookDTO entity) {
        BookModel bookModel = new BookModel(entity);

        Link create = linkTo(methodOn(BookController.class).createBook(entity)).withRel("create_book");
        Link get = linkTo(methodOn(BookController.class).getBook(entity.id)).withRel("get_book");
        Link update = linkTo(methodOn(BookController.class).updateBook(entity.id, entity)).withRel("update_book");
        Link delete = linkTo(methodOn(BookController.class).deleteBook(entity.id)).withRel("delete_book");

        bookModel.add(create, get, update, delete);

        return bookModel;
    }
}