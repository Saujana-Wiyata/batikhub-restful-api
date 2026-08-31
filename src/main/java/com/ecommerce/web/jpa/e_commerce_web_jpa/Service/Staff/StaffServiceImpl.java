package com.ecommerce.web.jpa.e_commerce_web_jpa.service.staff;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Staff;
import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.enums.Role;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffUpdateRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.repositories.StaffRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Service
@Validated
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    @Transactional
    public void insert(@Valid StaffRequest staffReq) {

        String geenerateId = UUID.randomUUID().toString()
                .substring(0, 8);

        Staff staff = new Staff(geenerateId, staffReq.getName(), staffReq.getEmail(),
                staffReq.getPassword(), Role.valueOf(staffReq.getRole()), null, null);

        staffRepository.save(staff);
    }

    @Override
    @Transactional
    public StaffResponse update(@NotBlank String token, @Valid StaffUpdateRequest staffReq) {

        Staff staff = staffRepository.findByToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Fail to delete your account"));

        if (!staffReq.getName().isBlank())
            staff.setName(staffReq.getName());

        if (!staffReq.getEmail().isBlank())
            staff.setEmail(staffReq.getEmail());

        if (!staffReq.getPassword().isBlank())
            staff.setPassword(staffReq.getPassword());

        if (!staffReq.getRole().isBlank())
            staff.setRole(Role.valueOf(staffReq.getRole()));

        Staff staffSave = staffRepository.save(staff);

        return new StaffResponse(staffSave.getName(), staffSave.getEmail(), staff.getRole());
    }

    @Override
    @Transactional
    public void delete(@Valid String token) {
        Staff staff = staffRepository.findByToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                        "Fail to delete your account"));

        staffRepository.delete(staff);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StaffResponse> findAll() {

        PageRequest paging = PageRequest.of(0, 5);

        return staffRepository.findAll(paging)
                .map(t -> StaffResponse.builder()
                        .name(t.getName())
                        .email(t.getEmail())
                        .role(t.getRole())
                        .build());
    }

}
