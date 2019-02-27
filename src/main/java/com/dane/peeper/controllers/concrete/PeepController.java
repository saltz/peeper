package com.dane.peeper.controllers.concrete;

import com.dane.peeper.domain.models.Peep;
import com.dane.peeper.domain.services.interfaces.IPeepService;
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

    @Autowired
    public PeepController(IPeepService service) {
        this.service = service;
    }

    @GetMapping(path = "peeps", produces = "application/json")
    public @ResponseBody
    HttpEntity<List<Peep>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "peeps/{id}", produces = "application/json")
    public @ResponseBody
    HttpEntity<Peep> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "users/{userId}/peeps", produces = "application/json")
    public @ResponseBody
    HttpEntity<List<Peep>> getAllUserPeeps(@PathVariable UUID userId) {
        return new ResponseEntity<>(service.findAllUserPeeps(userId), HttpStatus.OK);
    }

    @PostMapping(path = "users/{userId}/peeps", produces = "application/json", consumes = "application/json")
    public @ResponseBody HttpEntity<Peep> createPeep(@PathVariable UUID userId, @RequestBody Peep peep) {
         return new ResponseEntity<>(service.createPeep(userId, peep), HttpStatus.CREATED);
    }
}
