package com.dane.peeper.controllers.v1_1;

import com.dane.peeper.controllers.abstractions.ResourceBaseController;
import com.dane.peeper.domain.extensions.BindingResultExtension;
import com.dane.peeper.domain.models.entities.User;
import com.dane.peeper.domain.models.requestModels.UserRequestModel;
import com.dane.peeper.domain.models.resourceModels.UserResource;
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
import java.util.stream.Collectors;

@RestController("user_controller_v1.1")
@RequestMapping(path = "v1.1/users")
public class UserController extends ResourceBaseController<UserResource> {
    private final IUserService service;
    private final ModelMapper mapper;

    @Autowired
    public UserController(IUserService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody
    HttpEntity getAll() {
        List<UserViewModel> response = mapper.map(service.findAll(), new TypeToken<List<UserViewModel>>() {
        }.getType());
        return returnResourceCollection(convertToResourceCollection(response), HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public @ResponseBody
    HttpEntity getById(@PathVariable UUID id) throws Exception {
        UserViewModel user = mapper.map(service.findById(id), UserViewModel.class);
        return new ResponseEntity<>(new UserResource(user), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody
    HttpEntity create(@RequestBody @Valid UserRequestModel user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(BindingResultExtension.returnBindingErrorMessages(bindingResult), HttpStatus.BAD_REQUEST);
        }

        UserViewModel userVewModel = mapper.map(service.create(mapper.map(user, User.class)), UserViewModel.class);
        return new ResponseEntity<>(new UserResource(userVewModel), HttpStatus.CREATED);
    }

    @PatchMapping(path = "{id}", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    HttpEntity update(@PathVariable UUID id, @Valid @RequestBody UserRequestModel user, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(BindingResultExtension.returnBindingErrorMessages(bindingResult), HttpStatus.BAD_REQUEST);
        }

        UserViewModel userViewModel = mapper.map(service.softUpdate(id, mapper.map(user, User.class)), UserViewModel.class);
        return new ResponseEntity<>(new UserResource(userViewModel), HttpStatus.OK);
    }

    @GetMapping(path = "{id}/followers", produces = "application/json")
    public @ResponseBody
    HttpEntity getFollowers(@PathVariable UUID id) throws Exception {
        List<UserViewModel> response = mapper.map(service.getFollowers(id), new TypeToken<List<UserViewModel>>(){}.getType());
        return returnResourceCollection(convertToResourceCollection(response), HttpStatus.OK);
    }

    @GetMapping(path = "{id}/following", produces = "application/json")
    public @ResponseBody
    HttpEntity getFollowing(@PathVariable UUID id) throws Exception {
        List<UserViewModel> response = mapper.map(service.getFollowing(id), new TypeToken<List<UserViewModel>>() {}.getType());
        return returnResourceCollection(convertToResourceCollection(response), HttpStatus.OK);

    }

    @PostMapping(path = "{id}/following/{followId}", produces = "application/json")
    public @ResponseBody
    HttpEntity followUser(@PathVariable UUID id, @PathVariable UUID followId) throws Exception {
        UserViewModel response = mapper.map(service.followUser(id, followId), UserViewModel.class);
        return new ResponseEntity<>(new UserResource(response), HttpStatus.OK);
    }

    private List<UserResource> convertToResourceCollection(List<UserViewModel> viewModels) {
        return viewModels.stream().map(r -> {
            try {
                return new UserResource(r);
            } catch (Exception e) {
                return null;
            }
        }).collect(Collectors.toList());
    }
}