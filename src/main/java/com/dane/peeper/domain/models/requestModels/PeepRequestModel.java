package com.dane.peeper.domain.models.requestModels;

import lombok.Data;

import java.util.Date;

@Data
public class PeepRequestModel {
    public String text;
    public Date date;
}
