package com.ecommerce.web.jpa.e_commerce_web_jpa.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.PagingResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.WebResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk.ProdukInsertRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk.ProdukResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk.ProdukUpdateRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.produk.ProdukService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class ProdukController {

        private final ProdukService produkService;

        @PostMapping(path = "/api/v1/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public WebResponse<String> insertProduk(@ModelAttribute ProdukInsertRequest produkInsert) {

                produkService.insert(produkInsert);
                return WebResponse.<String>builder()
                                .code(HttpStatus.OK.value())
                                .data("OK")
                                .build();
        }

        @PatchMapping(path = "/api/v1/products/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public WebResponse<ProdukResponse> updateDataProduk(
                        @PathVariable String id,
                        @ModelAttribute ProdukUpdateRequest produkReq) {

                ProdukResponse update = produkService.update(id, produkReq);
                return WebResponse.<ProdukResponse>builder()
                                .code(HttpStatus.OK.value())
                                .data(update)
                                .build();
        }

        @GetMapping(path = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
        public WebResponse<List<ProdukResponse>> getAllProduk() {

                Page<ProdukResponse> products = produkService.findAll();

                PagingResponse paging = PagingResponse.builder()
                                .currentPage(products.getNumber())
                                .size(products.getSize())
                                .totalPage(products.getTotalPages())
                                .totalElements((int) products.getTotalElements())
                                .build();

                return WebResponse.<List<ProdukResponse>>builder()
                                .code(HttpStatus.OK.value())
                                .data(products.getContent())
                                .paging(paging)
                                .build();
        }

        @GetMapping(path = "/api/v1/products/search", produces = MediaType.APPLICATION_JSON_VALUE)
        public WebResponse<List<ProdukResponse>> findProdukByName(
                        @RequestParam(name = "name", required = false) String produkName) {

                Page<ProdukResponse> produk = produkService.findByNama(produkName);

                PagingResponse paging = PagingResponse.builder()
                                .currentPage(produk.getNumber())
                                .size(produk.getSize())
                                .totalElements((int) produk.getTotalElements())
                                .totalPage(produk.getTotalPages())
                                .build();

                return WebResponse.<List<ProdukResponse>>builder()
                                .code(HttpStatus.OK.value())
                                .paging(paging)
                                .data(produk.getContent())
                                .build();
        }

        @DeleteMapping(path = "/api/v1/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public WebResponse<String> deleteProduk(@PathVariable(name = "id") String idProduk) {

                produkService.delete(idProduk);
                return WebResponse.<String>builder()
                                .code(HttpStatus.OK.value())
                                .data("OK")
                                .build();
        }
}
