package com.dane.peeper.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
public class Peep {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    @Getter
    @Setter
    public String text;
    @Getter
    @Setter
    public Calendar date;
    @Getter
    @Setter
    @OneToOne
    public User owner;
    @Getter
    @Setter
    @OneToMany
    public List<User> likes;
    @Getter
    @Setter
    @OneToMany
    public List<User> reporters;

    public Peep() {}
}
