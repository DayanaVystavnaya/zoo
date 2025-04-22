package com.luv2code.springboot.demo.myzooapp.controller;

import com.luv2code.springboot.demo.myzooapp.model.Owner;
import com.luv2code.springboot.demo.myzooapp.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    // ✅ Создать нового владельца
    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        Owner created = ownerService.createOwner(owner);
        return ResponseEntity.ok(created);
    }

    // ✅ Получить всех владельцев
    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    // ✅ Получить владельца по ID
    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.getOwnerById(id));
    }
}
