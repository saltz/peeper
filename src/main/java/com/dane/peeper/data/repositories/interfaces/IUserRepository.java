package com.dane.peeper.data.repositories.interfaces;

import com.dane.peeper.domain.models.Peep;
import com.dane.peeper.domain.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface IUserRepository extends CrudRepository<User, UUID> {
}
