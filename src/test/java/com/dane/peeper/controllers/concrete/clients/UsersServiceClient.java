package com.dane.peeper.controllers.concrete.clients;

import com.dane.peeper.controllers.concrete.wrappers.UserCollection;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsersServiceClient {
    private final RestTemplate restTemplate;

    public UsersServiceClient(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public HttpEntity<UserCollection> getUsers() {
        return restTemplate.getForEntity("/users", UserCollection.class);
    }
}
