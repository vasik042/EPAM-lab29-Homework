package com.epam.hw3.controllers.assemblers;

import com.epam.hw3.DTOs.UserDTO;
import com.epam.hw3.controllers.UserController;
import com.epam.hw3.controllers.models.UserModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler extends RepresentationModelAssemblerSupport <UserDTO, UserModel> {

    public UserAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    public UserModel toModel(UserDTO entity) {
        UserModel userModel = new UserModel(entity);

        Link create = linkTo(methodOn(UserController.class).createUser(entity)).withRel("create_user");
        Link get = linkTo(methodOn(UserController.class).getUser(entity.email)).withRel("get_user");
        Link update = linkTo(methodOn(UserController.class).updateUser(entity.email, entity)).withRel("update_user");
        Link delete = linkTo(methodOn(UserController.class).deleteUser(entity.email)).withRel("delete_user");

        userModel.add(create, get, update, delete);

        return userModel;
    }
}
