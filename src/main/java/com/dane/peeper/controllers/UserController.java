package com.dane.peeper.controllers;

import com.dane.peeper.models.User;
import com.dane.peeper.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody
    List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public @ResponseBody
    User getUserById(@PathVariable UUID id) {
        return service.getUserById(id);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    User CreateUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable UUID id) {
        service.deleteUserById(id);
    }
}
