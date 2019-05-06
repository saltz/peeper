package com.dane.peeper.controllers.v1;

import com.dane.peeper.domain.extensions.BindingResultExtension;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController("user_controller_v1.0")
@RequestMapping(path = "v1/users")
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
    HttpEntity<Iterable<UserViewModel>> getAll() {
        List<UserViewModel> response = mapper.map(service.findAll(), new TypeToken<List<UserViewModel>>(){}.getType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public @ResponseBody
    HttpEntity<UserViewModel> getById(@PathVariable UUID id) throws Exception {
        return new ResponseEntity<>(mapper.map(service.findById(id), UserViewModel.class), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody
    HttpEntity create(@RequestBody @Valid UserRequestModel user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(BindingResultExtension.returnBindingErrorMessages(bindingResult), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(mapper.map(service.create(mapper.map(user, User.class)), UserViewModel.class), HttpStatus.CREATED);
    }

    @PatchMapping(path = "{id}", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    HttpEntity hardUpdate(@PathVariable UUID id, @RequestBody UserRequestModel user) throws Exception {
        return new ResponseEntity<>(mapper.map(service.softUpdate(id, mapper.map(user, User.class)), UserViewModel.class), HttpStatus.OK);
    }


    @DeleteMapping(path = "{id}")
    public HttpEntity deleteById(@PathVariable UUID id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "{id}/followers", produces = "application/json")
    public @ResponseBody
    HttpEntity<Iterable<UserViewModel>> getFollowers(@PathVariable UUID id) throws Exception {
        List<UserViewModel> response = mapper.map(service.getFollowers(id), new TypeToken<List<UserViewModel>>(){}.getType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "{id}/following", produces = "application/json")
    public @ResponseBody
    HttpEntity<Iterable<UserViewModel>> getFollowing(@PathVariable UUID id) throws Exception {
        List<UserViewModel> response = mapper.map(service.getFollowing(id), new TypeToken<List<UserViewModel>>() {}.getType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "{id}/following/{followId}", produces = "application/json")
    public @ResponseBody
    HttpEntity<UserViewModel> followUser(@PathVariable UUID id, @PathVariable UUID followId) throws Exception {
        return new ResponseEntity<>(mapper.map(service.followUser(id, followId), UserViewModel.class), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}/following/{followId}")
    public @ResponseBody
    HttpEntity unFollowUser(@PathVariable UUID id, @PathVariable UUID followId) throws Exception {
        service.unFollowUser(id, followId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
