package com.dane.peeper.domain.models.requestModels;

import com.dane.peeper.domain.models.entities.UserRole;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserRequestModel {
    @Size(min = 2, max = 30)
    public String alias;
    @NotNull
    @Size(min = 2, max = 30)
    public String firstName;
    @NotNull
    @Size(min = 2, max = 40)
    public String lastName;
    @NotNull
    @Email
    public String email;
    @NotNull
    public String hash;
    @Size(max = 160)
    public String biography;
    @Size(max = 70)
    public String website;
    public Byte[] picture;
    @NotNull
    public UserRole role;
}
