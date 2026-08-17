package com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Staff;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Staff.StaffRequestDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Staff.StaffUpdateDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Staff;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Enum.Role;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Repositories.StaffRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public void insert(@Valid StaffRequestDTO staffReq) {

        String geenerateId = UUID.randomUUID().toString()
                .substring(0, 8);

        Staff staff = new Staff(geenerateId, staffReq.getName(), staffReq.getEmail(),
                staffReq.getPassword(), Role.valueOf(staffReq.getRole()));

        staffRepository.save(staff);
    }

    @Override
    public Staff findByEmailAndPassword(@NotBlank String email, @NotBlank String password) {

        return staffRepository.findByEmailAndPassword(email, password);

    }

    @Override
    public Staff findById(@NotBlank String idStaff) {
        return staffRepository.findById(idStaff).orElse(null);
    }

    @Override
    public Staff update(@NotBlank String id, @Valid StaffUpdateDTO staffReq) {

        Staff staff = staffRepository.findById(id).orElse(null);

        if (!staffReq.getName().isBlank())
            staff.setName(staffReq.getName());

        if (!staffReq.getEmail().isBlank())
            staff.setEmail(staffReq.getEmail());

        if (!staffReq.getPassword().isBlank())
            staff.setPassword(staffReq.getPassword());

        return staffRepository.save(staff);
    }

    @Override
    public void delete(@Valid String id) {
        Staff staff = staffRepository.findById(id).orElse(null);

        staffRepository.delete(staff);
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

}
