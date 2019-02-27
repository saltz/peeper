package com.dane.peeper.domain.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String streetName;
    public int streetNumber;
    public String postalCode;
    public String cityName;
}
