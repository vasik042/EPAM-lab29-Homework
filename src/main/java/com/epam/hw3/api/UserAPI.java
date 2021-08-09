package com.epam.hw3.api;

import com.epam.hw3.DTOs.UserDTO;
import com.epam.hw3.controllers.models.UserModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Users management API")
@RequestMapping("/users")
public interface UserAPI {

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserModel createUser(@Valid @RequestBody UserDTO userDTO);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email")
    })
    @ApiOperation("Get user")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    UserModel getUser(@PathVariable String email);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email")
    })
    @ApiOperation("Update user")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{email}", method = RequestMethod.PUT)
    UserModel updateUser(@PathVariable String email, @Valid @RequestBody UserDTO userDTO);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email")
    })
    @ApiOperation("Delete user")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser(@PathVariable String email);
}
