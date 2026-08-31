package com.ecommerce.web.jpa.e_commerce_web_jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Staff;

public interface StaffRepository extends JpaRepository<Staff, String> {

    Optional<Staff> findByEmailAndPassword(String email, String password);

    Optional<Staff> findByToken(String token);
}
