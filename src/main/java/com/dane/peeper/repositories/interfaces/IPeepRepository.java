package com.dane.peeper.repositories.interfaces;

import com.dane.peeper.models.Peep;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IPeepRepository extends CrudRepository<Peep, UUID> {
}
