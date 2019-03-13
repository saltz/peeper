package com.dane.peeper.domain.models.requestModels;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PeepRequestModel {
    @NotEmpty
    @Size(min = 2, max = 140)
    public String text;
}
