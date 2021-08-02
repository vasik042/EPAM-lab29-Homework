package com.epam.hw3.services.imp;

import com.epam.hw3.DTOs.UserDTO;
import com.epam.hw3.models.User;
import com.epam.hw3.repositories.UserRepository;
import com.epam.hw3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO findUser(String email) {
        User user = userRepository.findUserByEmail(email);
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userRepository.save(new User(userDTO.firstName, userDTO.lastName, userDTO.username, userDTO.email, userDTO.password));
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        userRepository.updateUser(userDTO.firstName, userDTO.lastName, userDTO.password, userDTO.username, userDTO.email);
        return userDTO;
    }

    @Override
    public void deleteUser(String email) {
        userRepository.delete(userRepository.findUserByEmail(email));
    }
}
