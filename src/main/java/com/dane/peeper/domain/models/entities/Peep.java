package com.dane.peeper.domain.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Peep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String text;
    public Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User owner;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "like_user_id")
    public Set<User> likes;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_user_id")
    public Set<User> reporters;

    public Peep() {}

    public Peep(UUID id, String text, Date date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

}
