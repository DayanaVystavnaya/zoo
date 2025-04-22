package com.luv2code.springboot.demo.myzooapp.repository;

import com.luv2code.springboot.demo.myzooapp.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // Найти по виду
    List<Animal> findBySpecies(String species);

    // Найти всех старше определённого возраста
    List<Animal> findByAgeGreaterThanEqual(int age);

    // Комбинированный поиск
    List<Animal> findBySpeciesAndAgeGreaterThanEqual(String species, int age);
}
