package com.dane.peeper.domain.services.concrete;

import com.dane.peeper.data.repositories.interfaces.IUserRepository;
import com.dane.peeper.domain.exceptions.InvalidRelationException;
import com.dane.peeper.domain.models.entities.User;
import com.dane.peeper.utils.ModelUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private IUserRepository repository;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void findAll() {
        List<User> users = new ArrayList<>(Arrays.asList(ModelUtilities.createFakeUser(UUID.randomUUID()), ModelUtilities.createFakeUser(UUID.randomUUID())));

        when(repository.findAll()).thenReturn(users);

        List<User> result = (List<User>) service.findAll();

        Assert.assertEquals(users, result);
        Assert.assertNotEquals(0, result.size());
        Assert.assertEquals(users.get(0), result.get(0));
    }

    @Test
    public void findById() throws Exception {
        User user = ModelUtilities.createFakeUser(UUID.randomUUID());

        when(repository.findById(user.id)).thenReturn(Optional.of(user));

        User result = service.findById(user.id);

        Assert.assertEquals(user, result);
    }

    @Test
    public void create() {
        User user = ModelUtilities.createFakeUser(UUID.randomUUID());

        when(repository.save(user)).thenReturn(user);

        User result = service.create(user);

        assertEquals(user, result);
    }

    @Test
    public void followUser() throws Exception {
        User firstUser = ModelUtilities.createFakeUser(UUID.randomUUID());
        User secondUser = ModelUtilities.createFakeUser(UUID.randomUUID());
        firstUser.following = new HashSet<>();

        when(repository.findById(firstUser.id)).thenReturn(Optional.of(firstUser));
        when(repository.findById(secondUser.id)).thenReturn(Optional.of(secondUser));
        when(repository.save(firstUser)).thenReturn(firstUser);

        User result = service.followUser(firstUser.id, secondUser.id);

        Assert.assertEquals(secondUser, result);
    }

    @Test
    public void followUrSelf() {
        User user = ModelUtilities.createFakeUser(UUID.randomUUID());

        try {
            service.followUser(user.id, user.id);
        } catch (Exception e) {
            Assert.assertEquals( "it is not possible to follow yourself", e.getMessage());
        }
    }
}