package com.dane.peeper.domain.models.viewModels;

import com.dane.peeper.domain.models.entities.UserRole;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserViewModel {
    public UUID id;
    public String alias;
    public String firstName;
    public String lastName;
    public String email;
    public String biography;
    public String website;
    public Byte[] picture;
    public UserRole role;
}
