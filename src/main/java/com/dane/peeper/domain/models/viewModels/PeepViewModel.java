package com.dane.peeper.domain.models.viewModels;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PeepViewModel {
    public UUID id;
    public String text;
    public Date date;
    public UserViewModel owner;
}
