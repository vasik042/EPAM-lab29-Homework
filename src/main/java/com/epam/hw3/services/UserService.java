package com.epam.hw3.services;

import com.epam.hw3.DTOs.UserDTO;

public interface UserService {
    UserDTO findUser(String email);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUser(String email);
}
