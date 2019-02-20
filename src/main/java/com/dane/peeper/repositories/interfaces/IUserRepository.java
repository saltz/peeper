package com.dane.peeper.repositories.interfaces;

import com.dane.peeper.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IUserRepository extends CrudRepository<User, UUID> {
}
