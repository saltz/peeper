package com.dane.peeper.domain.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Peep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String text;
    public Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User owner;
    @OneToMany
    public List<User> likes;
    @OneToMany
    public List<User> reporters;

    public Peep() {}

    public Peep(UUID id, String text, Date date) {
        this.id = id;
        this.text = text;
        this.date = date;
    }

}
