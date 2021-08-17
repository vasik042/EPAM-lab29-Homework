package com.epam.hw3.services;

import com.epam.hw3.DTOs.UserDTO;
import com.epam.hw3.controllers.models.UserModel;
import java.util.List;

public interface UserService {
    UserModel findUser(String email);

    List<UserModel> findTwo(int page);

    List<UserModel> findAllSortedByName();

    UserModel createUser(UserDTO userDTO);

    UserModel updateUser(UserDTO userDTO);

    void deleteUser(String email);
}
