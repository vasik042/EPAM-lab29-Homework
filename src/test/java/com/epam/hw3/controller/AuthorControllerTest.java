package com.epam.hw3.controller;

import com.epam.hw3.DTOs.AuthorDTO;
import com.epam.hw3.controllers.AuthorController;
import com.epam.hw3.controllers.assemblers.AuthorAssembler;
import com.epam.hw3.controllers.models.AuthorModel;
import com.epam.hw3.services.AuthorService;
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
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @MockBean
    private AuthorService authorService;

    private final AuthorAssembler authorAssembler = new AuthorAssembler();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAuthor() throws Exception {
        AuthorDTO authorDTO = DataUtil.createAuthor().toDTO();
        AuthorModel authorModel = authorAssembler.toModel(authorDTO);

        when(authorService.findAuthor(authorDTO.id)).thenReturn(authorModel);

        mockMvc.perform(get("/authors/" + authorDTO.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(authorDTO.id));
    }

    @Test
    void createAuthorTest() throws Exception {
        AuthorDTO authorDTO = DataUtil.createAuthor().toDTO();

        mockMvc.perform(post("/authors/")
                        .content(DataUtil.asJsonString(authorDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void getAll() throws Exception {
        ArrayList<AuthorModel> authors = new ArrayList<>();
        authors.add(authorAssembler.toModel(DataUtil.createAuthor().toDTO()));

        when(authorService.findAll()).thenReturn(authors);

        mockMvc.perform(get("/authors/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..id").value(authors.get(0).getAuthorDTO().id));
    }
}
