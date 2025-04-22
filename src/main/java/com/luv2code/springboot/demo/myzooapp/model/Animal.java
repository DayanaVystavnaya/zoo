package com.luv2code.springboot.demo.myzooapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;
    private int age;

    @ManyToOne
    private Owner owner;

    @ManyToMany
    @JoinTable(
            name = "animal_friendships",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<Animal> friends = new HashSet<>();


}
