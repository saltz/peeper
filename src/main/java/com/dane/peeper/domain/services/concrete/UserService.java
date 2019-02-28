package com.dane.peeper.domain.services.concrete;

import com.dane.peeper.data.repositories.interfaces.IUserRepository;
import com.dane.peeper.domain.models.entities.User;
import com.dane.peeper.domain.services.interfaces.IUserService;
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
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User create(User object) {
        return repository.save(object);
    }

    @Override
    public User hardUpdate(User object) {
        return repository.save(object);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> getFollowers(UUID id) {
        User user = repository.findById(id).orElse(null);
        return user.followers;
    }

    @Override
    public List<User> getFollowing(UUID id) {
        User user = repository.findById(id).orElse(null);
        return user.following;
    }

    @Override
    public User followUser(UUID id, UUID followId) {
        if (id.equals(followId)) {
            return null;
        }

        User user = repository.findById(id).orElse(null);
        User userToFollow = repository.findById(followId).orElse(null);

        user.followers.add(userToFollow);
        repository.save(user);
        return userToFollow;
    }

    @Override
    public void unFollowUser(UUID userId, UUID followId) {
        User user = repository.findById(userId).orElse(null);
        user.followers.remove(user.followers.stream().filter(u -> u.id.equals(followId)).findFirst().orElse(null));
        repository.save(user);
    }
}


