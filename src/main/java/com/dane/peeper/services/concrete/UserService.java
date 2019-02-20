package com.dane.peeper.services.concrete;

import com.dane.peeper.models.User;
import com.dane.peeper.repositories.interfaces.IUserRepository;
import com.dane.peeper.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final IUserRepository repository;

    @Autowired
    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User getUserById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        repository.save(user);
        return user;
    }

    @Override
    public void deleteUserById(UUID id) {
        repository.deleteById(id);
    }
}
