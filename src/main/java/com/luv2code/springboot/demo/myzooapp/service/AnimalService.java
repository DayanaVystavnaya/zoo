package com.luv2code.springboot.demo.myzooapp.service;

import com.luv2code.springboot.demo.myzooapp.model.Animal;
import com.luv2code.springboot.demo.myzooapp.model.Owner;  // Импорт модели Owner
import com.luv2code.springboot.demo.myzooapp.repository.AnimalRepository;
import com.luv2code.springboot.demo.myzooapp.repository.OwnerRepository;  // Импорт OwnerRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private OwnerRepository ownerRepository;  // Внедрение зависимости OwnerRepository

    // ✅ Создать новое животное
    public Animal createAnimal(String name, String species, int age) {
        Animal animal = new Animal();
        return animalRepository.save(animal);
    }

    // ✅ Получить все животные
    public List<Animal> getAll() {
        return animalRepository.findAll();
    }

    // ✅ Получить животное по ID
    public Animal getById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Животное не найдено с ID: " + id));
    }

    // ✅ Фильтрация по виду
    public List<Animal> filterBySpecies(String species) {
        return animalRepository.findBySpecies(species);
    }

    // ✅ Фильтрация по минимальному возрасту
    public List<Animal> filterByMinAge(int minAge) {
        return animalRepository.findByAgeGreaterThanEqual(minAge);
    }

    // ✅ Фильтрация по виду и минимальному возрасту
    public List<Animal> filterBySpeciesAndMinAge(String species, int minAge) {
        return animalRepository.findBySpeciesAndAgeGreaterThanEqual(species, minAge);
    }

    // ✅ Добавить друга
    public Animal addFriend(Long id, Long friendId) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Животное не найдено с ID: " + id));
        Animal friend = animalRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("Друг не найден с ID: " + friendId));

        animal.getFriends().add(friend); // добавляем друга в список друзей
        friend.getFriends().add(animal); // добавляем животное в список друзей другого животного

        animalRepository.save(animal); // сохраняем изменения
        animalRepository.save(friend); // сохраняем изменения

        return animal;
    }

    // ✅ Назначить владельца животному
    public Animal assignOwner(Long animalId, Long ownerId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Животное не найдено с ID: " + animalId));

        // Проверяем наличие владельца в репозитории
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Владелец не найден с ID: " + ownerId));

        animal.setOwner(owner); // назначаем владельца
        return animalRepository.save(animal); // сохраняем изменения
    }
}
