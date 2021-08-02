package com.epam.hw3.controllers;

import com.epam.hw3.DTOs.UserDTO;
import com.epam.hw3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public UserDTO getUser(@PathVariable String email) {
        return userService.findUser(email);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{email}", method = RequestMethod.PUT)
    public UserDTO updateUser(@PathVariable String email, @RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}
