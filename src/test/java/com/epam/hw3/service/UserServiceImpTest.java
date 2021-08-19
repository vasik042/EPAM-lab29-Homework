package com.epam.hw3.service;

import com.epam.hw3.DTOs.UserDTO;
import com.epam.hw3.controllers.assemblers.UserAssembler;
import com.epam.hw3.controllers.models.UserModel;
import com.epam.hw3.models.Errors.PasswordsNotMatchException;
import com.epam.hw3.models.Errors.UserNotFoundException;
import com.epam.hw3.models.User;
import com.epam.hw3.repositories.UserRepository;
import com.epam.hw3.services.imp.UserServiceImp;
import com.epam.hw3.util.DataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImpTest {
    @InjectMocks
    private UserServiceImp userServiceImp;

    @Mock
    private UserRepository userRepository;
    @Spy
    private UserAssembler userAssembler;

    @Test
    void getUserTest() {
        User user = DataUtil.createUser(1);

        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(user);

        UserModel userModel = userServiceImp.findUser(user.getEmail());

        assertThat(userModel.getUserDTO(), allOf(
                hasProperty("email", equalTo(user.getEmail())),
                hasProperty("username", equalTo(user.getUsername())),
                hasProperty("firstName", equalTo(user.getFirstName())),
                hasProperty("lastName", equalTo(user.getLastName()))));
    }

    @Test
    void getUserUserNotFoundTest() {
        when(userRepository.findUserByEmail("email")).thenReturn(null);

        assertThrows(UserNotFoundException.class, () -> userServiceImp.findUser("email"));
    }

    @Test
    void getTwoUsersTest() {
        List<User> list = new ArrayList<>();
        list.add(DataUtil.createUser(1));
        list.add(DataUtil.createUser(2));

        when(userRepository.findAll(PageRequest.of(0, 2))).thenReturn(new PageImpl<>(list));

        List<UserModel> two = userServiceImp.findTwo(1);

        assertThat(two.size(), is(2));

        assertThat(two.get(0).getUserDTO(), allOf(
                hasProperty("email", equalTo(list.get(0).getEmail())),
                hasProperty("username", equalTo(list.get(0).getUsername())),
                hasProperty("firstName", equalTo(list.get(0).getFirstName())),
                hasProperty("lastName", equalTo(list.get(0).getLastName()))));

        assertThat(two.get(1).getUserDTO(), allOf(
                hasProperty("email", equalTo(list.get(1).getEmail())),
                hasProperty("username", equalTo(list.get(1).getUsername())),
                hasProperty("firstName", equalTo(list.get(1).getFirstName())),
                hasProperty("lastName", equalTo(list.get(1).getLastName()))));
    }

    @Test
    void getAllUsersSortedByNameTest() {
        List<User> list = new ArrayList<>();
        list.add(DataUtil.createUser(1));
        list.add(DataUtil.createUser(2));

        when(userRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"))).thenReturn(list);

        List<UserModel> two = userServiceImp.findAllSortedByName();

        assertThat(two.size(), is(2));

        assertThat(two.get(0).getUserDTO(), allOf(
                hasProperty("email", equalTo(list.get(0).getEmail())),
                hasProperty("username", equalTo(list.get(0).getUsername())),
                hasProperty("firstName", equalTo(list.get(0).getFirstName())),
                hasProperty("lastName", equalTo(list.get(0).getLastName()))));

        assertThat(two.get(1).getUserDTO(), allOf(
                hasProperty("email", equalTo(list.get(1).getEmail())),
                hasProperty("username", equalTo(list.get(1).getUsername())),
                hasProperty("firstName", equalTo(list.get(1).getFirstName())),
                hasProperty("lastName", equalTo(list.get(1).getLastName()))));
    }

    @Test
    void createUserPasswordsNotMatchTest() {
        User user = DataUtil.createUser(1);
        UserDTO userDTO = user.toDTO();
        userDTO.password = "1";
        userDTO.repeatPassword = "2";

        assertThrows(PasswordsNotMatchException.class, () -> userServiceImp.createUser(userDTO));
    }
}
