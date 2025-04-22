package com.luv2code.springboot.demo.myzooapp.controller;

import com.luv2code.springboot.demo.myzooapp.model.Animal;
import com.luv2code.springboot.demo.myzooapp.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // ✅ Создать новое животное
    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        Animal createdAnimal = animalService.createAnimal(animal.getName(), animal.getSpecies(), animal.getAge());
        return ResponseEntity.ok(createdAnimal);
    }

    // ✅ Получить все животные
    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        List<Animal> animals = animalService.getAll();
        return ResponseEntity.ok(animals);
    }

    // ✅ Получить животное по ID
    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        Animal animal = animalService.getById(id);
        return ResponseEntity.ok(animal);
    }

    // ✅ Фильтрация животных по виду
    @GetMapping("/filter")
    public ResponseEntity<List<Animal>> filterAnimals(
            @RequestParam(required = false) String species,
            @RequestParam(required = false) Integer minAge) {

        if (species != null && minAge != null) {
            return ResponseEntity.ok(animalService.filterBySpeciesAndMinAge(species, minAge));
        } else if (species != null) {
            return ResponseEntity.ok(animalService.filterBySpecies(species));
        } else if (minAge != null) {
            return ResponseEntity.ok(animalService.filterByMinAge(minAge));
        } else {
            return ResponseEntity.ok(animalService.getAll()); // если параметры не указаны, возвращаем всех животных
        }
    }

    // ✅ Добавить друга животному
    @PostMapping("/{id}/add-friend/{friendId}")
    public ResponseEntity<?> addFriend(@PathVariable Long id, @PathVariable Long friendId) {
        animalService.addFriend(id, friendId);
        return ResponseEntity.ok().build();
    }

    // ✅ Назначить владельца животному
    @PostMapping("/{animalId}/assign-owner/{ownerId}")
    public ResponseEntity<Animal> assignOwner(@PathVariable Long animalId, @PathVariable Long ownerId) {
        Animal updatedAnimal = animalService.assignOwner(animalId, ownerId);
        return ResponseEntity.ok(updatedAnimal);
    }
}
