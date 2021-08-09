package com.epam.hw3.controllers.assemblers;

import com.epam.hw3.DTOs.AuthorDTO;
import com.epam.hw3.controllers.AuthorController;
import com.epam.hw3.controllers.models.AuthorModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AuthorAssembler extends RepresentationModelAssemblerSupport<AuthorDTO, AuthorModel> {

    public AuthorAssembler() {
        super(AuthorController.class, AuthorModel.class);
    }

    @Override
    public AuthorModel toModel(AuthorDTO entity) {
        AuthorModel authorModel = new AuthorModel(entity);

        Link create = linkTo(methodOn(AuthorController.class).createAuthor(entity)).withRel("create_author");
        Link get = linkTo(methodOn(AuthorController.class).getAuthor(entity.id)).withRel("get_author");
        Link update = linkTo(methodOn(AuthorController.class).updateAuthor(entity.id, entity)).withRel("update_author");
        Link delete = linkTo(methodOn(AuthorController.class).deleteAuthor(entity.id)).withRel("delete_author");

        authorModel.add(create, get, update, delete);

        return authorModel;
    }
}
