package com.luv2code.springboot.demo.myzooapp.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactInfo;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Animal> animals = new ArrayList<>();
}
