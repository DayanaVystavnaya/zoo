package com.luv2code.springboot.demo.myzooapp.service;

import com.luv2code.springboot.demo.myzooapp.dto.OwnerDTO;
import com.luv2code.springboot.demo.myzooapp.model.Owner;
import com.luv2code.springboot.demo.myzooapp.repository.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private static final Logger logger = LoggerFactory.getLogger(OwnerService.class);

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner createOwner(OwnerDTO dto) {
        logger.info("Creating new owner: {}", dto);

        Owner owner = new Owner();
        owner.setName(dto.getName());
        owner.setContactInfo(dto.getContactInfo());

        Owner saved = ownerRepository.save(owner);
        logger.debug("Owner created with ID: {}", saved.getId());
        return saved;
    }

    public List<Owner> getAllOwners() {
        logger.info("Fetching all owners");
        return ownerRepository.findAll();
    }

    public Owner getOwnerById(Long id) {
        logger.info("Fetching owner by ID: {}", id);

        return ownerRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Owner not found with ID: {}", id);
                    return new RuntimeException("Owner not found: " + id);
                });
    }

    public Owner updateOwner(Long id, OwnerDTO dto) {
        logger.info("Updating owner with ID: {}", id);

        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Owner not found with ID: {}", id);
                    return new RuntimeException("Owner not found: " + id);
                });

        owner.setName(dto.getName());
        owner.setContactInfo(dto.getContactInfo());

        Owner updated = ownerRepository.save(owner);
        logger.debug("Owner updated: {}", updated);
        return updated;
    }

    public void deleteOwner(Long id) {
        logger.warn("Deleting owner with ID: {}", id);

        if (!ownerRepository.existsById(id)) {
            logger.error("Owner not found with ID: {}", id);
            throw new RuntimeException("Owner not found: " + id);
        }

        ownerRepository.deleteById(id);
        logger.info("Owner with ID {} deleted", id);
    }
}
