package com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Staff;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Enum.Role;

@SpringBootTest
public class StaffRepoTest {

    @Autowired
    private StaffRepository staffRepository;

    @Test
    void insert() {
        Staff staff = new Staff();
        staff.setId("S02");
        staff.setName("Abu");
        staff.setEmail("staff2@gmail.com");
        staff.setPassword("secret2");
        staff.setRole(Role.FINANCE);

        staffRepository.save(staff);
    }

    @Test
    void findByEmailAndPassword() {
        Staff staff = staffRepository
                .findByEmailAndPassword("staff1@gmail.com", "secret");

        Assertions.assertNotNull(staff);
    }

    @Test
    void findByEmailAndPasswordNotMatch() {
        Staff staff = staffRepository
                .findByEmailAndPassword("staff1@gmail.com", "secret2");

        Assertions.assertNull(staff);
    }

    @Test
    void updateRole() {
        Staff staff = staffRepository.findById("S02").orElse(null);
        staff.setRole(Role.STAFF);
        staffRepository.save(staff);

        Assertions.assertEquals(Role.STAFF, staffRepository
                .findById("S02").orElse(null).getRole());
    }

    @Test
    void update() {
        Staff staff = staffRepository.findById("S01").orElse(null);
        staff.setPassword("SECRET");
        staffRepository.save(staff);

        Assertions.assertEquals("SECRET", staffRepository.findById("S01")
                .orElse(null).getPassword());
    }

    @Test
    void findByIdTest() {
        Staff staff = staffRepository.findById("S01").orElse(null);

        Assertions.assertNotNull(staff);
    }

    @Test
    void findByIdNotFound() {
        Staff staff = staffRepository.findById("S1").orElse(null);

        Assertions.assertNull(staff);
    }

    @Test
    void remove() {
        Staff staff = staffRepository.findById("S02").orElse(null);

        staffRepository.delete(staff);
    }
}
