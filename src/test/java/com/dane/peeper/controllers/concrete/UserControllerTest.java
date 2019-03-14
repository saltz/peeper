package com.dane.peeper.controllers.concrete;

import com.dane.peeper.controllers.v1.UserController;
import com.dane.peeper.domain.models.entities.User;
import com.dane.peeper.domain.services.interfaces.IUserService;
import com.dane.peeper.utils.ModelUtilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IUserService service;

    @MockBean
    private ModelMapper mapper;

    @Before
    public void setUp() {

    }

    @Test
    public void getAllUsers() throws Exception{
        List<User> users = new ArrayList<>(Arrays.asList(ModelUtilities.createFakeUser(UUID.randomUUID()), ModelUtilities.createFakeUser(UUID.randomUUID())));

        given(service.findAll()).willReturn(users);

        mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}