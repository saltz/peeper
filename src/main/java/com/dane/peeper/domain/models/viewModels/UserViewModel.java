package com.dane.peeper.domain.models.viewModels;

import com.dane.peeper.domain.models.entities.UserRole;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Data
public class UserViewModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String alias;
    public String firstName;
    public String lastName;
    public String email;
    public String hash;
    public String biography;
    public String website;
    public Byte[] picture;
    public UserRole role;
}
