package com.dane.peeper.domain.models.resourceModels;

import com.dane.peeper.domain.models.viewModels.PeepViewModel;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Relation(collectionRelation = "peeps")
public class PeepResource extends ResourceSupport {
    public PeepViewModel peep;

    public PeepResource(PeepViewModel peep) throws Exception {
        this.peep = peep;

        final UUID id = peep.id;
        final UUID ownerId = peep.owner.id;

        this.peep.owner = null;

        add(linkTo(methodOn(com.dane.peeper.controllers.v1_1.PeepController.class).getById(id)).withSelfRel());
        add(linkTo(methodOn(com.dane.peeper.controllers.v1_1.UserController.class).getById(ownerId)).withRel("owner"));
    }
}