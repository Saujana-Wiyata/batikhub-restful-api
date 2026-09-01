package com.ecommerce.web.jpa.e_commerce_web_jpa.service.produk;

import org.springframework.data.domain.Page;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk.ProdukInsertRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk.ProdukResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk.ProdukUpdateRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public interface ProdukService {

    void insert(@Valid ProdukInsertRequest produk);

    ProdukResponse findById(@NotBlank String id);

    void kurangiStock(@Positive int jmlhPembelian, @NotBlank String idProduk);

    Page<ProdukResponse> findByNama(@NotBlank String nameProduk);

    Page<ProdukResponse> findAll();

    ProdukResponse update(@NotBlank String id, ProdukUpdateRequest produk);

    void delete(@NotBlank String id);

}
