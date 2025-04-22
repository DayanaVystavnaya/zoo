package com.luv2code.springboot.demo.myzooapp.service;

import com.luv2code.springboot.demo.myzooapp.model.Owner;
import com.luv2code.springboot.demo.myzooapp.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Owner getOwnerById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Владелец не найден с ID: " + id));
    }
}
