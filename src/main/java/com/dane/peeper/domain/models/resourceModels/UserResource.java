package com.dane.peeper.domain.models.resourceModels;

import com.dane.peeper.controllers.v1.PeepController;
import com.dane.peeper.domain.models.viewModels.UserViewModel;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Relation(collectionRelation = "users")
public class UserResource extends ResourceSupport {
    public UserViewModel user;

    public UserResource(UserViewModel user) throws Exception {
        this.user = user;

        final UUID id = user.id;

        add(linkTo(methodOn(com.dane.peeper.controllers.v1.UserController.class).getById(id)).withSelfRel());
        add(linkTo(methodOn(PeepController.class).getAllUserPeeps(id)).withRel("peeps"));
        add(linkTo(methodOn(com.dane.peeper.controllers.v1.UserController.class).getFollowers(id)).withRel("followers"));
        add(linkTo(methodOn(com.dane.peeper.controllers.v1.UserController.class).getFollowing(id)).withRel("following"));
    }
}
