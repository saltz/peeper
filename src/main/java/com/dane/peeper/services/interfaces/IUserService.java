package com.dane.peeper.services.interfaces;

import com.dane.peeper.models.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<User> getUsers();
    User getUserById(UUID id);
    User createUser(User user);
    void deleteUserById(UUID id);
}
