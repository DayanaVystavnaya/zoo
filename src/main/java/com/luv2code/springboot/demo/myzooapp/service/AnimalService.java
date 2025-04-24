package com.luv2code.springboot.demo.myzooapp.service;

import com.luv2code.springboot.demo.myzooapp.dto.AnimalDTO;
import com.luv2code.springboot.demo.myzooapp.model.Animal;
import com.luv2code.springboot.demo.myzooapp.model.Owner;
import com.luv2code.springboot.demo.myzooapp.repository.AnimalRepository;
import com.luv2code.springboot.demo.myzooapp.repository.OwnerRepository;
import com.luv2code.springboot.demo.myzooapp.repository.specification.AnimalSpecification;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class AnimalService {

    private static final Logger logger = LoggerFactory.getLogger(AnimalService.class);

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public Animal createAnimal(@Valid AnimalDTO dto) {
        logger.info("Creating new animal: {}", dto);

        validateAnimalName(dto.getName());
        Animal animal = buildAnimalFromDto(new Animal(), dto);
        Animal saved = animalRepository.save(animal);

        logger.debug("Animal created with ID: {}", saved.getId());
        return saved;
    }

    public Animal updateAnimal(Long id, @Valid AnimalDTO dto) {
        logger.info("Updating animal with ID: {}", id);

        Animal animal = findAnimalById(id);
        validateAnimalName(dto.getName());
        buildAnimalFromDto(animal, dto);
        Animal updated = animalRepository.save(animal);

        logger.debug("Animal updated: {}", updated);
        return updated;
    }

    public void deleteAnimal(Long id) {
        logger.warn("Deleting animal with ID: {}", id);

        if (!animalRepository.existsById(id)) {
            logger.error("Animal not found with ID: {}", id);
            throw new RuntimeException("Animal not found: " + id);
        }
        animalRepository.deleteById(id);
        logger.info("Animal with ID {} deleted successfully", id);
    }

    public List<Animal> getAll() {
        logger.info("Retrieving all animals");
        return animalRepository.findAll();
    }

    public Animal getById(Long id) {
        logger.info("Getting animal by ID: {}", id);
        return findAnimalById(id);
    }

    public List<Animal> filterAnimals(String species, Integer minAge) {
        logger.info("Filtering animals by species={} and minAge={}", species, minAge);
        Specification<Animal> spec = AnimalSpecification.withFilters(species, minAge);
        return animalRepository.findAll(spec);
    }

    public Animal addFriend(Long id, Long friendId) {
        logger.info("Adding friend: animalId={} friendId={}", id, friendId);

        Animal animal = findAnimalById(id);
        Animal friend = findAnimalById(friendId);

        animal.getFriends().add(friend);
        friend.getFriends().add(animal);

        animalRepository.save(friend);
        Animal result = animalRepository.save(animal);

        logger.debug("Friendship created between animal {} and {}", id, friendId);
        return result;
    }

    public Animal assignOwner(Long animalId, Long ownerId) {
        logger.info("Assigning owner: animalId={} ownerId={}", animalId, ownerId);

        Animal animal = findAnimalById(animalId);
        Owner owner = findOwnerById(ownerId);

        animal.setOwner(owner);
        Animal updated = animalRepository.save(animal);

        logger.debug("Owner {} assigned to animal {}", ownerId, animalId);
        return updated;
    }

    private Animal buildAnimalFromDto(Animal animal, AnimalDTO dto) {
        animal.setName(dto.getName().trim());
        animal.setSpecies(dto.getSpecies());
        animal.setAge(dto.getAge());
        return animal;
    }

    private void validateAnimalName(String name) {
        if (name == null || name.trim().isEmpty()) {
            logger.warn("Validation failed: empty animal name");
            throw new IllegalArgumentException("Animal name must not be empty");
        }
    }

    private Animal findAnimalById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Animal not found with ID: {}", id);
                    return new RuntimeException("Animal not found: " + id);
                });
    }

    private Owner findOwnerById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Owner not found with ID: {}", id);
                    return new RuntimeException("Owner not found: " + id);
                });
    }
}
