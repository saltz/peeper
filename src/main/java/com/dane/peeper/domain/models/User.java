package com.dane.peeper.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String alias;
    public String firstName;
    public String lastName;
    public String email;
    public String hash;
    public String biography;
    public String website;
    public Byte[] picture;
    public UserRole role;

    @OneToOne
    public Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true)
    public List<Peep> peeps;
    @ManyToMany
    public List<User> followers;
    @ManyToMany
    public List<User> following;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addPeep(Peep peep) {
        peeps.add(peep);
        peep.owner = this;
    }

    public void removePeep(Peep peep) {
        peeps.remove(peep);
        peep.owner = null;
    }
}
