package com.epam.hw3.services;

import com.epam.hw3.DTOs.UserDTO;
import com.epam.hw3.controllers.models.UserModel;

public interface UserService {
    UserModel findUser(String email);

    UserModel createUser(UserDTO userDTO);

    UserModel updateUser(UserDTO userDTO);

    void deleteUser(String email);
}
