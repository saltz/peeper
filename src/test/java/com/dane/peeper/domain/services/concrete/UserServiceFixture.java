package com.dane.peeper.domain.services.concrete;

import com.dane.peeper.data.repositories.interfaces.IUserRepository;
import com.dane.peeper.domain.models.entities.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceFixture {

    @InjectMocks
    private UserService service;

    @Mock
    private IUserRepository repository;

    @Before
    public void setUp(){
        initMocks(this);
    }

    private User createFakeUser(UUID id) {
        User user = new User();
        user.id = id;
        user.firstName = "test";
        user.lastName = "test";
        return user;
    }


    @Test
    public void findAll() {
        List<User> users = new ArrayList<>(Arrays.asList(createFakeUser(UUID.randomUUID()), createFakeUser(UUID.randomUUID())));

        when(repository.findAll()).thenReturn(users);

        List<User> result = service.findAll();

        Assert.assertEquals(users, result);
        Assert.assertNotEquals(0, result.size());
        Assert.assertEquals(users.get(0), result.get(0));
    }

    @Test
    public void findById() {
        User user = createFakeUser(UUID.randomUUID());

        when(repository.findById(user.id)).thenReturn(Optional.of(user));

        User result = service.findById(user.id);

        Assert.assertEquals(user, result);
    }

    @Test
    public void create() {
        User user = createFakeUser(UUID.randomUUID());

        when(repository.save(user)).thenReturn(user);

        User result = service.create(user);

        assertEquals(user, result);
    }
}