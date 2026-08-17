package com.ecommerce.web.jpa.e_commerce_web_jpa.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Staff.StaffRequestDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Staff.StaffUpdateDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Staff;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Staff.StaffService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class StaffServiceTest {

    @Autowired
    private StaffService staffService;

    @Test
    void testInsertSuccess() {
        StaffRequestDTO staffRequestDTO = new StaffRequestDTO();
        staffRequestDTO.setEmail("khairy@gmail.com");
        staffRequestDTO.setPassword("khairy123");
        staffRequestDTO.setName("Khairy Aimar");
        staffRequestDTO.setRole("HR");

        staffService.insert(staffRequestDTO);
    }

    @Test
    void testInsertFail() {
        StaffRequestDTO staffRequestDTO = new StaffRequestDTO();
        staffRequestDTO.setPassword("abu");
        staffRequestDTO.setName("  ");
        staffRequestDTO.setEmail("abu.com");
        staffRequestDTO.setRole("STAFF");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            staffService.insert(staffRequestDTO);
        });

    }

    @Test
    void testFindByEmailAndPasswordSuccess() {
        Staff staff = staffService
                .findByEmailAndPassword("test@gmail.com", "test");

        Assertions.assertNotNull(staff);
        Assertions.assertEquals(staff.getName(), "Ali");
    }

    @Test
    void testFindByEmailAndPasswordButEmailBlank() {

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            staffService.findByEmailAndPassword("  ", "test");
        });
    }

    @Test
    void testFindByEmailAndPasswordButPasswordBlank() {

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            staffService.findByEmailAndPassword("test@gmail.com", "  ");
        });
    }

    @Test
    void testFindByEmailAndPasswordNotMatch() {
        Staff staff = staffService
                .findByEmailAndPassword("test@gmail.com", "hihih");

        Assertions.assertNull(staff);
    }

    @Test
    void testFindByIdSuccess() {
        Staff byId = staffService.findById("6cea3ae3");

        Assertions.assertNotNull(byId);
        Assertions.assertEquals(byId.getName(), "Ali");
    }

    @Test
    void testUpdate() {

        StaffUpdateDTO staff = new StaffUpdateDTO();
        staff.setName("");
        staff.setEmail("");
        staff.setPassword("test");
        staff.setRole("");

        Staff update = staffService.update("6cea3ae3", staff);

        Assertions.assertEquals(update.getName(), "Test");
    }

    @Test
    void testUpdatNotSuccess() {
        StaffUpdateDTO staff = new StaffUpdateDTO();
        staff.setName("");
        staff.setEmail("test.com");
        staff.setPassword("");
        staff.setRole("");

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            staffService.update("6cea3ae3", staff);
        });
    }

    @Test
    void testDeleteSuccess() {
        staffService.delete("769c7f0d");
    }

    @Test
    void testDeleteFail() {
        Assertions.assertThrows(Exception.class, () -> {
            staffService.delete("769c7f0d");
        });
    }
}
