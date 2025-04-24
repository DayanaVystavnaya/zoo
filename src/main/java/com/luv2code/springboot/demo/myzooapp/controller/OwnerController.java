package com.luv2code.springboot.demo.myzooapp.controller;

import com.luv2code.springboot.demo.myzooapp.dto.OwnerDTO;
import com.luv2code.springboot.demo.myzooapp.model.Owner;
import com.luv2code.springboot.demo.myzooapp.service.OwnerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private static final Logger logger = LoggerFactory.getLogger(OwnerController.class);

    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public ResponseEntity<Owner> createOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
        logger.info("Creating owner with data: {}", ownerDTO);
        Owner created = ownerService.createOwner(ownerDTO);
        logger.debug("Owner created with ID: {}", created.getId());
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        logger.info("Fetching all owners");
        List<Owner> owners = ownerService.getAllOwners();
        logger.debug("Total owners found: {}", owners.size());
        return ResponseEntity.ok(owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        logger.info("Fetching owner with ID: {}", id);
        Owner owner = ownerService.getOwnerById(id);
        logger.debug("Owner found: {}", owner);
        return ResponseEntity.ok(owner);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @Valid @RequestBody OwnerDTO ownerDTO) {
        logger.info("Updating owner with ID: {}, new data: {}", id, ownerDTO);
        Owner updated = ownerService.updateOwner(id, ownerDTO);
        logger.debug("Owner updated: {}", updated);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        logger.warn("Deleting owner with ID: {}", id);
        ownerService.deleteOwner(id);
        logger.info("Owner with ID {} deleted", id);
        return ResponseEntity.noContent().build();
    }
}
