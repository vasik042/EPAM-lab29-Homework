package com.epam.hw3.controller;

import com.epam.hw3.DTOs.AuthorDTO;
import com.epam.hw3.DTOs.BookDTO;
import com.epam.hw3.controllers.AuthorController;
import com.epam.hw3.controllers.BookController;
import com.epam.hw3.controllers.assemblers.AuthorAssembler;
import com.epam.hw3.controllers.assemblers.BookAssembler;
import com.epam.hw3.controllers.models.AuthorModel;
import com.epam.hw3.controllers.models.BookModel;
import com.epam.hw3.services.AuthorService;
import com.epam.hw3.services.BookService;
import com.epam.hw3.util.DataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    private final BookAssembler bookAssembler = new BookAssembler();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getBook() throws Exception {
        BookDTO bookDTO = DataUtil.createBook().toDTO();
        BookModel bookModel = bookAssembler.toModel(bookDTO);

        when(bookService.findBook(bookDTO.id)).thenReturn(bookModel);

        mockMvc.perform(get("/books/" + bookDTO.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(bookDTO.id));
    }

    @Test
    void createBookTest() throws Exception {
        BookDTO bookDTO = DataUtil.createBook().toDTO();

        mockMvc.perform(post("/books/")
                        .content(DataUtil.asJsonString(bookDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getAll() throws Exception {
        ArrayList<BookModel> books = new ArrayList<>();
        books.add(bookAssembler.toModel(DataUtil.createBook().toDTO()));

        when(bookService.findAllBooks()).thenReturn(books);

        mockMvc.perform(get("/books/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..id").value(books.get(0).getBookDTO().id));
    }
}
