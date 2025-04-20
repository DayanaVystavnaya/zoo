package com.luv2code.springboot.demo.myzooapp.controller;

import com.luv2code.springboot.demo.myzooapp.model.Animal;
import com.luv2code.springboot.demo.myzooapp.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animals") // address + requestMapping
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    // CREATE
    @PostMapping
    public Animal create(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    // READ all
    @GetMapping
    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    // READ by id
    @GetMapping("/{id}")
    public Animal getById(@PathVariable Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Animal update(@PathVariable Long id, @RequestBody Animal updatedAnimal) {
        Optional<Animal> optional = animalRepository.findById(id);
        if (optional.isPresent()) {
            Animal animal = optional.get();
            animal.setName(updatedAnimal.getName());
            animal.setSpecies(updatedAnimal.getSpecies());
            animal.setAge(updatedAnimal.getAge());
            return animalRepository.save(animal);
        }
        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        animalRepository.deleteById(id);
    }
}
