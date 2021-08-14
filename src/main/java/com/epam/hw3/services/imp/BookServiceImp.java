package com.epam.hw3.services.imp;

import com.epam.hw3.DTOs.BookDTO;
import com.epam.hw3.controllers.assemblers.BookAssembler;
import com.epam.hw3.controllers.models.BookModel;
import com.epam.hw3.models.Book;
import com.epam.hw3.repositories.AuthorRepository;
import com.epam.hw3.repositories.BookRepository;
import com.epam.hw3.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImp implements BookService {
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    BookAssembler bookAssembler;
    EntityManager entityManager;

    @Autowired
    public BookServiceImp(BookRepository bookRepository,AuthorRepository authorRepository, BookAssembler bookAssembler, EntityManager entityManager) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookAssembler = bookAssembler;
        this.entityManager = entityManager;
    }

    @Override
    public BookModel findBook(int id) {
        Book book = bookRepository.findById(id).get();
        return bookAssembler.toModel(book.toDTO());
    }

    public List<BookModel> findAllBooks(){
        Query query = entityManager.createNamedQuery("findAllOrderedByName", Book.class);
        List<Book> resultList = query.getResultList();

        return resultList.stream().map(b -> bookAssembler.toModel(b.toDTO())).collect(Collectors.toList());
    }

    @Override
    public BookModel createBook(BookDTO bookDTO) {
        Book book = bookRepository.save(new Book(bookDTO.name, bookDTO.description, authorRepository.getById(bookDTO.authorId)));
        return bookAssembler.toModel(book.toDTO());
    }

    @Override
    public BookModel updateBook(BookDTO bookDTO) {
        bookRepository.updateBook(bookDTO.name, bookDTO.description, bookDTO.authorId, bookDTO.id);
        return bookAssembler.toModel(bookDTO);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
