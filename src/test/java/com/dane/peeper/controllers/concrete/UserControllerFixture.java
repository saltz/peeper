package com.dane.peeper.controllers.concrete;

import com.dane.peeper.controllers.concrete.clients.UsersServiceClient;
import com.dane.peeper.controllers.concrete.wrappers.UserCollection;
import com.dane.peeper.domain.models.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(UsersServiceClient.class)
public class UserControllerFixture {

    @Autowired
    private UsersServiceClient client;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {

    }

    @Test
    public void whenCallingAllUsers() throws Exception{
        String detailsString = objectMapper.writeValueAsString(new ArrayList<>(Arrays.asList(new User("John", "Smith"))));
        this.server.expect(requestTo("/users")).andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));

        HttpEntity<UserCollection> users = client.getUsers();
        Assert.assertNotNull(users);
    }

}