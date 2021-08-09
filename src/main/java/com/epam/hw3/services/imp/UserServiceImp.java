package com.epam.hw3.services.imp;

import com.epam.hw3.DTOs.UserDTO;
import com.epam.hw3.controllers.assemblers.UserAssembler;
import com.epam.hw3.controllers.models.UserModel;
import com.epam.hw3.models.Errors.PasswordsNotMatchException;
import com.epam.hw3.models.Errors.UserNotFoundException;
import com.epam.hw3.models.User;
import com.epam.hw3.repositories.UserRepository;
import com.epam.hw3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    UserRepository userRepository;
    UserAssembler userAssembler;

    @Autowired
    public UserServiceImp(UserRepository userRepository, UserAssembler userAssembler) {
        this.userRepository = userRepository;
        this.userAssembler = userAssembler;
    }

    public UserModel findUser(String email) {
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new UserNotFoundException();
        }
        return userAssembler.toModel(new UserDTO(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail()));
    }

    @Override
    public UserModel createUser(UserDTO userDTO) {
        if (userDTO.repeatPassword.equals(userDTO.password)){
            User user = userRepository.save(new User(userDTO.firstName, userDTO.lastName, userDTO.username, userDTO.email, userDTO.password));
            return userAssembler.toModel(new UserDTO(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail()));
        }else {
            throw new PasswordsNotMatchException();
        }
    }

    @Override
    public UserModel updateUser(UserDTO userDTO) {
        userRepository.updateUser(userDTO.firstName, userDTO.lastName, userDTO.password, userDTO.username, userDTO.email);
        return userAssembler.toModel(userDTO);
    }

    @Override
    public void deleteUser(String email) {
        userRepository.delete(userRepository.findUserByEmail(email));
    }
}
