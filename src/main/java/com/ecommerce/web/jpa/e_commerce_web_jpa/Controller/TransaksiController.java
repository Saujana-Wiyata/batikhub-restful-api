package com.ecommerce.web.jpa.e_commerce_web_jpa.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.PagingResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.WebResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.transaksi.TransaksiInsertRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.transaksi.TransaksiResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.transaksi.TransaksiService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
public class TransaksiController {

    private final TransaksiService transaksiService;

    @PostMapping(path = "/api/v1/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> createTransaksi(@RequestBody TransaksiInsertRequest transaksiInsertReq) {

        transaksiService.insert(transaksiInsertReq);
        return WebResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .data("OK")
                .build();
    }

    @GetMapping(path = "/api/v1/transaction/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<TransaksiResponse>> getAllTransaksi() {

        Page<TransaksiResponse> all = transaksiService.findAll();

        PagingResponse paging = PagingResponse.builder()
                .currentPage(all.getNumber())
                .size(all.getSize())
                .totalElements((int) all.getTotalElements())
                .totalPage(all.getTotalPages())
                .build();

        return WebResponse.<List<TransaksiResponse>>builder()
                .code(HttpStatus.OK.value())
                .data(all.getContent())
                .paging(paging)
                .build();
    }

    @DeleteMapping(path = "/api/v1/transaction/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> deleteTransaksi(
            @PathVariable(name = "id") int idTransaksi) {

        transaksiService.delete(idTransaksi);
        return WebResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .data("OK")
                .build();

    }
}
