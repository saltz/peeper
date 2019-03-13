package com.dane.peeper.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserFollowException extends Exception {
    public UserFollowException(String message) {
        super(message);
    }
}
