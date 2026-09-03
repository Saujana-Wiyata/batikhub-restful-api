package com.ecommerce.web.jpa.e_commerce_web_jpa.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.PagingResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.WebResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.omset.OmsetResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.omset.OmsetService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
public class OmsetController {

    private final OmsetService omsetService;

    @GetMapping(path = "/api/v1/omset/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<OmsetResponse>> showAllDashboardDara() {

        Page<OmsetResponse> all = omsetService.findAll();

        PagingResponse paging = PagingResponse.builder()
                .currentPage(all.getNumber())
                .totalElements((int) all.getTotalElements())
                .size(all.getSize())
                .totalPage(all.getTotalPages())
                .build();

        return WebResponse.<List<OmsetResponse>>builder()
                .code(HttpStatus.OK.value())
                .data(all.getContent())
                .paging(paging)
                .build();
    }

}
