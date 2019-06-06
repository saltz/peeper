package com.dane.peeper.controllers.abstractions;

import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class ResourceBaseController<T> {
    protected HttpEntity returnResourceCollection(List<T> resourceCollection, HttpStatus status) {
        return new ResponseEntity<>(new Resources<>(resourceCollection), status);
    }
}
