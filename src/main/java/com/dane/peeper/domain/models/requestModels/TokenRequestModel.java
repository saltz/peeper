package com.dane.peeper.domain.models.requestModels;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TokenRequestModel {
    @NotEmpty
    public String email;
    @NotEmpty
    public String hash;
}
