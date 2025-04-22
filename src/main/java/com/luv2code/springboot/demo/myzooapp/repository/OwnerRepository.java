package com.luv2code.springboot.demo.myzooapp.repository;

import com.luv2code.springboot.demo.myzooapp.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
