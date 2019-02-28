package com.dane.peeper.controllers;

import com.dane.peeper.domain.models.entities.User;
import com.dane.peeper.domain.models.requestModels.UserRequestModel;
import com.dane.peeper.domain.models.viewModels.UserViewModel;
import com.dane.peeper.domain.services.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final IUserService service;
    private final ModelMapper mapper;

    @Autowired
    public UserController(IUserService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody
    HttpEntity<List<UserViewModel>> getAll() {
        List<UserViewModel> response = mapper.map(service.findAll(), new TypeToken<List<UserViewModel>>() {}.getType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public @ResponseBody
    HttpEntity<UserViewModel> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(mapper.map(service.findById(id), UserViewModel.class), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody
    HttpEntity<UserViewModel> create(@RequestBody UserRequestModel user) {
        return new ResponseEntity<>(mapper.map(service.create(mapper.map(user, User.class)), UserViewModel.class), HttpStatus.CREATED);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody
    HttpEntity<UserViewModel> hardUpdate(@RequestBody UserRequestModel user) {
        return new ResponseEntity<>(mapper.map(service.create(mapper.map(user, User.class)), UserViewModel.class), HttpStatus.OK);
    }


    @DeleteMapping(path = "{id}")
    public HttpEntity deleteById(@PathVariable UUID id) {
        service.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "{id}/followers", produces = "application/json")
    public @ResponseBody
    HttpEntity<List<UserViewModel>> getFollowers(@PathVariable UUID id) {
        List<UserViewModel> response = mapper.map(service.getFollowers(id), new TypeToken<List<UserViewModel>>() {}.getType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "{id}/following", produces = "application/json")
    public @ResponseBody
    HttpEntity<List<UserViewModel>> getFollowing(@PathVariable UUID id) {
        List<UserViewModel> response = mapper.map(service.getFollowing(id), new TypeToken<List<UserViewModel>>() {}.getType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "{id}/following/{followId}", produces = "application/json")
    public @ResponseBody
    HttpEntity<UserViewModel> followUser(@PathVariable UUID id, @PathVariable UUID followId) {
        return new ResponseEntity<>(mapper.map(service.followUser(id, followId), UserViewModel.class), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}/following{followId}")
    public @ResponseBody
    HttpEntity unFollowUser(@PathVariable UUID id, @PathVariable UUID followId) {
        service.unFollowUser(id,followId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
