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
    @Size(min = 2, max = 30, message = "alias must be between 2 and 30 characters")
    public String alias;
    @NotNull(message = "first_name must not be null or empty")
    @Size(min = 2, max = 30, message = "first_name must be between 2 and 30 characters")
    public String firstName;
    @NotNull(message = "last_name must not be null or empty")
    @Size(min = 2, max = 40, message = "last_name must be between 2 and 40 characters")
    public String lastName;
    @NotNull(message = "email must not be null or empty")
    @Email(message = "email must be a valid email")
    public String email;
    @NotNull(message = "hash must not be null")
    public String hash;
    @Size(max = 160, message = "biography must be shorter then 160 characters")
    public String biography;
    @Size(max = 70, message = "website must be shorter then 70 characters")
    public String website;
    public String picture;
    @NotNull(message = "user_role must not be null or empty")
    public UserRole userRole;
}
