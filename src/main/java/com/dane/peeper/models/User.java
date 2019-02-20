package com.dane.peeper.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    @Getter
    @Setter
    public String alias;
    @Getter
    @Setter
    public String firstName;
    @Getter
    @Setter
    public String lastName;
    @Getter
    @Setter
    public String email;
    @Getter
    @Setter
    public String hash;
    @Getter
    @Setter
    public String biography;
    @Getter
    @Setter
    public String website;
    @Getter
    @Setter
    public Byte[] picture;
    @Getter
    @Setter
    public UserRole role;
    @Getter
    @Setter
    @OneToOne
    public Address adres;
    @Getter
    @Setter
    @OneToMany
    public List<Peep> peeps;
    @Getter
    @Setter
    @ManyToMany
    public List<User> followers;
    @Getter
    @Setter
    @ManyToMany
    public List<User> following;

    public User() {}
}
