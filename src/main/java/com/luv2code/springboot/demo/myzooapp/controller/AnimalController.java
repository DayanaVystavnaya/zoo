package com.luv2code.springboot.demo.myzooapp.controller;

import com.luv2code.springboot.demo.myzooapp.dto.AnimalDTO;
import com.luv2code.springboot.demo.myzooapp.model.Animal;
import com.luv2code.springboot.demo.myzooapp.service.AnimalService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private static final Logger logger = LoggerFactory.getLogger(AnimalController.class);

    @Autowired
    private AnimalService animalService;

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@Valid @RequestBody AnimalDTO dto) {
        logger.info("Received request to create animal: {}", dto);
        Animal createdAnimal = animalService.createAnimal(dto);
        logger.debug("Animal created with ID: {}", createdAnimal.getId());
        return ResponseEntity.ok(createdAnimal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @Valid @RequestBody AnimalDTO dto) {
        logger.info("Received request to update animal ID {} with data: {}", id, dto);
        Animal updatedAnimal = animalService.updateAnimal(id, dto);
        logger.debug("Animal updated: {}", updatedAnimal);
        return ResponseEntity.ok(updatedAnimal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        logger.warn("Received request to delete animal with ID: {}", id);
        animalService.deleteAnimal(id);
        logger.info("Animal with ID {} deleted successfully", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        logger.info("Fetching all animals");
        return ResponseEntity.ok(animalService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        logger.info("Fetching animal by ID: {}", id);
        return ResponseEntity.ok(animalService.getById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Animal>> filterAnimals(
            @RequestParam(required = false) String species,
            @RequestParam(required = false) Integer minAge) {
        logger.info("Filtering animals by species: {} and minAge: {}", species, minAge);
        return ResponseEntity.ok(animalService.filterAnimals(species, minAge));
    }

    @PostMapping("/{id}/add-friend/{friendId}")
    public ResponseEntity<Void> addFriend(@PathVariable Long id, @PathVariable Long friendId) {
        logger.info("Adding friendship between animal {} and {}", id, friendId);
        animalService.addFriend(id, friendId);
        logger.info("Friendship added successfully between {} and {}", id, friendId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{animalId}/assign-owner/{ownerId}")
    public ResponseEntity<Animal> assignOwner(@PathVariable Long animalId, @PathVariable Long ownerId) {
        logger.info("Assigning owner {} to animal {}", ownerId, animalId);
        Animal updatedAnimal = animalService.assignOwner(animalId, ownerId);
        logger.info("Owner assigned successfully");
        return ResponseEntity.ok(updatedAnimal);
    }
}
