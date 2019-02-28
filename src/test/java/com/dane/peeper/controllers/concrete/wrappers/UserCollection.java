package com.dane.peeper.controllers.concrete.wrappers;

import com.dane.peeper.domain.models.entities.User;

import java.util.List;

public class UserCollection {
    private List<User> users;

    public UserCollection(List<User> users) {
        this.users = users;
    }
}
