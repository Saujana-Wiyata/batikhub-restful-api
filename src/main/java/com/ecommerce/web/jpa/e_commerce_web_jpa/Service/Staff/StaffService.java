package com.ecommerce.web.jpa.e_commerce_web_jpa.service.staff;

import org.springframework.data.domain.Page;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffUpdateRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public interface StaffService {

    void insert(@Valid StaffRequest staffReq);

    Page<StaffResponse> findAll();

    StaffResponse update(@NotBlank String token, @Valid StaffUpdateRequest staffReq);

    void delete(@Valid String id);

}
