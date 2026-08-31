package com.ecommerce.web.jpa.e_commerce_web_jpa.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.PagingResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.WebResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffUpdateRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.staff.StaffService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@RestController
public class StaffController {

    private final StaffService staffService;

    @PostMapping(path = "/api/v1/staff", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> insertStaff(@RequestBody StaffRequest staffRequest) {

        staffService.insert(staffRequest);
        return WebResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .data("OK")
                .build();
    }

    @PatchMapping(path = "/api/v1/staff/current", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<StaffResponse> updateStaff(
            @RequestBody StaffUpdateRequest staffUpdateRequest,
            @RequestHeader String token) {

        StaffResponse staffUpdate = staffService.update(token, staffUpdateRequest);
        return WebResponse.<StaffResponse>builder()
                .code(HttpStatus.OK.value())
                .data(staffUpdate)
                .build();
    }

    @GetMapping(path = "/api/v1/staff/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<StaffResponse>> getAllStaff() {

        Page<StaffResponse> all = staffService.findAll();

        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(all.getNumber())
                .totalPage(all.getTotalPages())
                .size(all.getSize())
                .totalElements((int) all.getTotalElements())
                .build();

        return WebResponse.<List<StaffResponse>>builder()
                .code(HttpStatus.OK.value())
                .data(all.getContent())
                .paging(pagingResponse)
                .build();
    }

    @DeleteMapping(path = "/api/v1/staff/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> deleteDataStaff(@RequestHeader String token) {

        staffService.delete(token);
        return WebResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .data("OK")
                .build();

    }

}
