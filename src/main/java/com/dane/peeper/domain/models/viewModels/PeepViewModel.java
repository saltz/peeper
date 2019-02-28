package com.dane.peeper.domain.models.viewModels;

import com.dane.peeper.domain.models.entities.User;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PeepViewModel {
    public UUID id;
    public String text;
    public Date date;
}
