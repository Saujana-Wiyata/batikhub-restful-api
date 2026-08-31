package com.ecommerce.web.jpa.e_commerce_web_jpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.enums.Role;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffUpdateRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.staff.StaffService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class StaffServiceTest {

    @Autowired
    private StaffService staffService;

    @Test
    void testInsertSuccess() {
        StaffRequest staffRequestDTO = new StaffRequest();
        staffRequestDTO.setEmail("khairy@gmail.com");
        staffRequestDTO.setPassword("khairy123");
        staffRequestDTO.setName("Khairy Aimar");
        staffRequestDTO.setRole("HR");

        staffService.insert(staffRequestDTO);
    }

    @Test
    void testInsertFail() {
        StaffRequest staffRequestDTO = new StaffRequest();
        staffRequestDTO.setPassword("abu");
        staffRequestDTO.setName(" ");
        staffRequestDTO.setEmail("abu.com");
        staffRequestDTO.setRole("STAFF");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            staffService.insert(staffRequestDTO);
        });

    }

    @Test
    void testUpdateSuccess() {

        StaffUpdateRequest staff = new StaffUpdateRequest();
        staff.setName("");
        staff.setEmail("");
        staff.setPassword("");
        staff.setRole("HR");

        StaffResponse update = staffService.update("47695a", staff);

        Assertions.assertEquals(update.getRole(), Role.HR);
    }

    @Test
    void testUpdateFail() {
        StaffUpdateRequest staff = new StaffUpdateRequest();
        staff.setName("");
        staff.setEmail("test.com");
        staff.setPassword("");
        staff.setRole("");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            staffService.update("47695a", staff);
        });
    }

    @Test
    void testDeleteSuccess() {
        staffService.delete("f262c505");
    }

    @Test
    void testDeleteFail() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            staffService.delete("769c7f0d");
        });
    }

    @Test
    void testFindAll() {
        Page<StaffResponse> all = staffService.findAll();

        assertEquals(0, all.getTotalElements());
    }
}
