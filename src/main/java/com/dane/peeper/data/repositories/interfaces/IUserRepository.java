package com.dane.peeper.data.repositories.interfaces;

import com.dane.peeper.domain.models.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IUserRepository extends CrudRepository<User, UUID> {
}
