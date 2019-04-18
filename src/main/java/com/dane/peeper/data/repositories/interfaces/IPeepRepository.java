package com.dane.peeper.data.repositories.interfaces;

import com.dane.peeper.domain.models.entities.Peep;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface IPeepRepository extends CrudRepository<Peep, UUID> {
    List<Peep> findAllByOwnerId(UUID id);
}
