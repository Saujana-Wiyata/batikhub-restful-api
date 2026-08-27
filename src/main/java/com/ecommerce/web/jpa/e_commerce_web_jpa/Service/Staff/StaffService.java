package com.ecommerce.web.jpa.e_commerce_web_jpa.service.staff;

import java.util.List;

import com.ecommerce.web.jpa.e_commerce_web_jpa.dto.staff.StaffRequestDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.dto.staff.StaffUpdateDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Staff;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public interface StaffService {

    void insert(@Valid StaffRequestDTO staffReq);

    List<Staff> findAll();

    Staff findByEmailAndPassword(@NotBlank String email, @NotBlank String password);

    Staff findById(@NotBlank String idStaff);

    Staff update(@NotBlank String id, @Valid StaffUpdateDTO staffReq);

    void delete(@Valid String id);

}
