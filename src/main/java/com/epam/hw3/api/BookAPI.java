package com.epam.hw3.api;

import com.epam.hw3.DTOs.BookDTO;
import com.epam.hw3.controllers.models.BookModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Books management API")
@RequestMapping("/books")
public interface BookAPI {

    @ApiOperation("Create book")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    BookModel createBook(@Valid @RequestBody BookDTO bookDTO);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Book id")
    })
    @ApiOperation("Get book")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    BookModel getBook(@PathVariable int id);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Book id")
    })
    @ApiOperation("Update book")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    BookModel updateBook(@PathVariable int id, @Valid @RequestBody BookDTO bookDTO);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Book id")
    })
    @ApiOperation("Delete book")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteBook(@PathVariable int id);
}