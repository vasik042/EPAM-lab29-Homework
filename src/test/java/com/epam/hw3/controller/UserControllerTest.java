package com.epam.hw3.controller;

import com.epam.hw3.DTOs.UserDTO;
import com.epam.hw3.controllers.UserController;
import com.epam.hw3.controllers.assemblers.UserAssembler;
import com.epam.hw3.controllers.models.UserModel;
import com.epam.hw3.models.User;
import com.epam.hw3.services.UserService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    private final UserAssembler userAssembler = new UserAssembler();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserTest() throws Exception {
        UserDTO userDTO = DataUtil.createUser(1).toDTO();
        UserModel userModel = userAssembler.toModel(userDTO);

        when(userService.findUser(userDTO.email)).thenReturn(userModel);

        mockMvc.perform(get("/users/" + userDTO.getEmail()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(userDTO.email));
    }

    @Test
    void createUserTest() throws Exception {
        UserDTO userDTO = DataUtil.createUser(1).toDTO();
        userDTO.password = "111AAAaaa";
        userDTO.repeatPassword = "111AAAaaa";
        userDTO.email = "someEmail@mail.com";

        mockMvc.perform(post("/users/")
                        .content(DataUtil.asJsonString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void createUserValidationExceptionTest() throws Exception {
        UserDTO userDTO = DataUtil.createUser(1).toDTO();

        mockMvc.perform(post("/users/")
                        .content(DataUtil.asJsonString(userDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$..errorType.length()").value("3"));
    }

    @Test
    void getAllUsersSortedByNameTest() throws Exception {
        ArrayList<UserModel> users = new ArrayList<>();
        users.add(userAssembler.toModel(DataUtil.createUser(1).toDTO()));

        when(userService.findAllSortedByName()).thenReturn(users);

        mockMvc.perform(get("/users/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..email").value(users.get(0).getUserDTO().email));
    }

    @Test
    void getTwoUsersTest() throws Exception {
        ArrayList<UserModel> users = new ArrayList<>();
        users.add(userAssembler.toModel(DataUtil.createUser(1).toDTO()));

        when(userService.findTwo(1)).thenReturn(users);

        mockMvc.perform(get("/users/page=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..email").value(users.get(0).getUserDTO().email));
    }
}
