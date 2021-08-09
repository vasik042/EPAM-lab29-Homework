package com.epam.hw3.controllers;

import com.epam.hw3.DTOs.UserDTO;
import com.epam.hw3.api.UserAPI;
import com.epam.hw3.controllers.models.UserModel;
import com.epam.hw3.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController implements UserAPI {

    UserService userService;
    Logger logger;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        logger = LoggerFactory.getLogger(UserController.class);
    }

    @Override
    public UserModel createUser(UserDTO userDTO) {
        logger.info("Create user with email: " + userDTO.email);
        return userService.createUser(userDTO);
    }

    @Override
    public UserModel getUser(String email) {
        logger.info("Get user with email: " + email);
        return userService.findUser(email);
    }

    @Override
    public UserModel updateUser(String email, UserDTO userDTO) {
        logger.info("Update user with email: " + email);
        return userService.updateUser(userDTO);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String email) {
        logger.info("Delete user with email: " + email);
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}
