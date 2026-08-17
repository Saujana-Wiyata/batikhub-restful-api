package com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Staff;

import java.util.List;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Staff.StaffRequestDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Staff.StaffUpdateDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Staff;

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
