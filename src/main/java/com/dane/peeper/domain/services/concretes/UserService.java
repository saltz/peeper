package com.dane.peeper.domain.services.concretes;

import com.dane.peeper.data.repositories.interfaces.IUserRepository;
import com.dane.peeper.domain.exceptions.InvalidRelationException;
import com.dane.peeper.domain.exceptions.UserFollowException;
import com.dane.peeper.domain.exceptions.UserNotFoundException;
import com.dane.peeper.domain.models.entities.User;
import com.dane.peeper.domain.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final IUserRepository repository;
    private final MailService mailService;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(IUserRepository repository, MailService mailService, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.mailService = mailService;
        this.encoder = encoder;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User findById(UUID id) throws Exception {
        return repository.findById(id).orElseThrow(() -> (new UserNotFoundException("no user exists with the supplied id")));
    }

    @Override
    public User create(User object) {
        object.hash = encoder.encode(object.hash);
        mailService.sendRegistrationEmail(object);

        return repository.save(object);
    }

    @Override
    public User hardUpdate(User object) {
        return repository.save(object);
    }

    @Override
    public User softUpdate(UUID id, User update) throws Exception {
        User current = repository.findById(id).orElseThrow(() -> (new UserNotFoundException("no user exists with the supplied id")));

        Field[] fields = update.getClass().getFields();

        for(Field field : fields) {
            Object value = field.get(update);

            if (value != null) {
                field.set(current, value);
            }
        }

        repository.save(current);

        return current;
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> getFollowers(UUID id) throws Exception {
        User user = repository.findById(id).orElseThrow(() -> (new UserNotFoundException("no user exists with the supplied id")));
        return user.followers;
    }

    @Override
    public List<User> getFollowing(UUID id) throws Exception {
        User user = repository.findById(id).orElseThrow(() -> (new UserNotFoundException("no user exists with the supplied id")));
        return user.following;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        return repository.findByEmail(email).orElseThrow(() -> (new UserNotFoundException("no user exists with the supplied email")));
    }

    @Override
    public User followUser(UUID id, UUID followId) throws Exception {
        if (id.equals(followId)) {
            throw new InvalidRelationException("it is not possible to follow yourself");
        }

        User user = repository.findById(id).orElseThrow(() -> (new UserNotFoundException("no user exists with the supplied id")));
        User userToFollow = repository.findById(followId).orElseThrow(() -> (new UserNotFoundException("no user exists with the supplied followId")));

        if (user.following.contains(userToFollow)) {
            throw new UserFollowException("you're already following this user");
        }

        user.following.add(userToFollow);
        repository.save(user);

        return userToFollow;
    }

    @Override
    public void unFollowUser(UUID userId, UUID followId) throws Exception {
        User user = repository.findById(userId).orElseThrow(() -> (new UserNotFoundException("no user exists with the supplied id")));
        user.following.remove(user.followers.stream().filter(u -> u.id.equals(followId)).findFirst().orElseThrow(() -> (new UserNotFoundException("no user exists with the supplied follow id"))));
        repository.save(user);
    }
}


