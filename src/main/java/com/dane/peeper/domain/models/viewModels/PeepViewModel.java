package com.dane.peeper.domain.models.viewModels;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PeepViewModel {
    public UUID id;
    public String text;
    public Date date;
    public UserViewModel owner;
}
