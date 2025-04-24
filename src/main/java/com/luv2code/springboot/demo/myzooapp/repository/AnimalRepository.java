package com.luv2code.springboot.demo.myzooapp.repository;

import com.luv2code.springboot.demo.myzooapp.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long>, JpaSpecificationExecutor<Animal> {

    // The custom query method that uses named parameters for filtering
    @Query("SELECT a FROM Animal a " +
            "WHERE (:species IS NULL OR a.species = :species) " +
            "AND (:minAge IS NULL OR a.age >= :minAge)")
    List<Animal> findWithFilters(@Param("species") String species,
                                 @Param("minAge") Integer minAge);
}
