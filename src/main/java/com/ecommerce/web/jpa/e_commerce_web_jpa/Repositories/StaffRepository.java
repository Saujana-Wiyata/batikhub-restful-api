package com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Staff;

public interface StaffRepository extends JpaRepository<Staff, String> {

    Staff findByEmailAndPassword(String email, String password);
}
