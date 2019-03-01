package com.dane.peeper.domain.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
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
    public String email;
    public String hash;
    public String biography;
    public String website;
    public Byte[] picture;
    public UserRole role;

    @OneToOne
    public Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true, fetch = FetchType.LAZY)
    public List<Peep> peeps;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    public Set<User> followers;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    public Set<User> following;

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
