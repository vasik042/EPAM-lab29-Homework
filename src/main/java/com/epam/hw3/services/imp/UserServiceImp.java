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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

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
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("id", user.getId());

        return userAssembler.toModel(user.toDTO());
    }

    @Override
    public List<UserModel> findTwo(int page) {
        Page<User> result = userRepository.findAll(PageRequest.of(page - 1, 2));

        return result.stream().map(u -> userAssembler.toModel(u.toDTO())).collect(Collectors.toList());
    }

    @Override
    public List<UserModel> findAllByName() {
        List<User> result = userRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));

        return result.stream().map(u -> userAssembler.toModel(u.toDTO())).collect(Collectors.toList());
    }

    @Override
    public UserModel createUser(UserDTO userDTO) {
        if (userDTO.repeatPassword.equals(userDTO.password)){
            User user = userRepository.save(new User(userDTO.firstName, userDTO.lastName, userDTO.username, userDTO.email, userDTO.password));
            return userAssembler.toModel(user.toDTO());
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
