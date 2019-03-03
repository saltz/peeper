package com.dane.peeper.domain.models.requestModels;

import com.dane.peeper.domain.models.entities.UserRole;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserRequestModel {
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
