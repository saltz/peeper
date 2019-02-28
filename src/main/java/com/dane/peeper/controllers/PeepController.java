package com.dane.peeper.controllers;

import com.dane.peeper.domain.models.entities.Peep;
import com.dane.peeper.domain.models.requestModels.PeepRequestModel;
import com.dane.peeper.domain.models.viewModels.PeepViewModel;
import com.dane.peeper.domain.services.interfaces.IPeepService;
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
public class PeepController {

    private final IPeepService service;
    private final ModelMapper mapper;

    @Autowired
    public PeepController(IPeepService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "peeps", produces = "application/json")
    public @ResponseBody
    HttpEntity<List<PeepViewModel>> getAll() {
        List<PeepViewModel> response = mapper.map(service.findAll(), new TypeToken<List<PeepViewModel>>(){}.getType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "peeps/{id}", produces = "application/json")
    public @ResponseBody
    HttpEntity<PeepViewModel> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(mapper.map(service.findById(id), PeepViewModel.class), HttpStatus.OK);
    }

    @DeleteMapping(path = "peeps/{id}")
    public @ResponseBody
    HttpEntity deletePeep(@PathVariable UUID id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "users/{userId}/peeps", produces = "application/json")
    public @ResponseBody
    HttpEntity<List<PeepViewModel>> getAllUserPeeps(@PathVariable UUID userId) {
        List<PeepViewModel> response = mapper.map(service.findAllUserPeeps(userId), new TypeToken<List<PeepViewModel>>(){}.getType());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "users/{userId}/peeps", produces = "application/json", consumes = "application/json")
    public @ResponseBody HttpEntity<PeepViewModel> createPeep(@PathVariable UUID userId, @RequestBody PeepRequestModel peep) {
         return new ResponseEntity<>(mapper.map(service.createPeep(userId, mapper.map(peep, Peep.class)), PeepViewModel.class), HttpStatus.CREATED);
    }
}