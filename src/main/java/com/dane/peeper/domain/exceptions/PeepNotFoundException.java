package com.dane.peeper.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PeepNotFoundException extends Exception {
    public PeepNotFoundException(String message) {
        super(message);
    }
}
