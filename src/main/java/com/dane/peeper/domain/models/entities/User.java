package com.dane.peeper.domain.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String alias;
    public String firstName;
    public String lastName;
    @Column(unique = true)
    public String email;
    public String hash;
    public String biography;
    public String website;
    public String picture;
    public UserRole role;

    @OneToOne
    public Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.LAZY)
    public List<Peep> peeps;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "following")
    public List<User> followers;

    @ManyToMany(fetch = FetchType.LAZY)
    public List<User> following;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
