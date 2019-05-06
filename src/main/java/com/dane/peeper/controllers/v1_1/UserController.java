package com.dane.peeper.controllers.v1_1;

import com.dane.peeper.domain.models.resourceModels.UserResource;
import com.dane.peeper.domain.models.viewModels.UserViewModel;
import com.dane.peeper.domain.services.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController("user_controller_v1.1")
@RequestMapping(path = "v1.1/users")
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
    ResponseEntity<Resources<UserResource>> getAll() {
        List<UserViewModel> response = mapper.map(service.findAll(), new TypeToken<List<UserViewModel>>() {
        }.getType());

        List<UserResource> resourceCollection = response.stream().map(r -> {
            try {
                return new UserResource(r);
            } catch (Exception e) {
                return null;
            }
        }).collect(Collectors.toList());

        return new ResponseEntity<>(new Resources<>(resourceCollection), HttpStatus.OK);
    }
}
