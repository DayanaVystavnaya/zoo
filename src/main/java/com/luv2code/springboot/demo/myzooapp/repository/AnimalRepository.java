package com.luv2code.springboot.demo.myzooapp.repository;

import com.luv2code.springboot.demo.myzooapp.model.Animal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {}
