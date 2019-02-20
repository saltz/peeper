package com.dane.peeper.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Address {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    @Getter
    @Setter
    public String streetName;
    @Getter
    @Setter
    public int streetNumber;
    @Getter
    @Setter
    public String postalCode;
    @Getter
    @Setter
    public String cityName;
}
