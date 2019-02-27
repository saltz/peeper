package com.dane.peeper.controllers.abstracts;

import com.dane.peeper.domain.services.interfaces.IBaseService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public abstract class BaseController<C, S extends IBaseService<C>> {
    protected final S service;

    public BaseController(S service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody
    HttpEntity<List<C>> getAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = "application/json")
    public @ResponseBody
    HttpEntity<C> getById(@PathVariable UUID id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody
    HttpEntity<C> create(@RequestBody C object) {
        return new ResponseEntity<>(service.create(object), HttpStatus.CREATED);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public @ResponseBody
    HttpEntity<C> hardUpdate(@RequestBody C object) {
        return new ResponseEntity<>(service.create(object), HttpStatus.OK);
    }


    @DeleteMapping(path = "{id}")
    public HttpEntity<C> deleteById(@PathVariable UUID id) {
        service.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}