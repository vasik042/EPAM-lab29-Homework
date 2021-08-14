package com.epam.hw3.api;

import com.epam.hw3.DTOs.AuthorDTO;
import com.epam.hw3.controllers.models.AuthorModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Authors management API")
@RequestMapping("/authors")
public interface AuthorAPI {

    @ApiOperation("Create author")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    AuthorModel createAuthor(@Valid @RequestBody AuthorDTO authorDTO);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Author id")
    })
    @ApiOperation("Get author")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    AuthorModel getAuthor(@PathVariable int id);

    @ApiOperation("Get all authors")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    List<AuthorModel> getAllAuthors();

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Author id")
    })
    @ApiOperation("Update author")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    AuthorModel updateAuthor(@PathVariable int id, @Valid @RequestBody AuthorDTO authorDTO);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Author id")
    })
    @ApiOperation("Delete author")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteAuthor(@PathVariable int id);
}