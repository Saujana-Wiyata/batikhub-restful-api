package com.ecommerce.web.jpa.e_commerce_web_jpa.service.omset;

import org.springframework.data.domain.Page;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.omset.OmsetRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.omset.OmsetResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public interface OmsetService {

    void insert(@Valid OmsetRequest omset);

    void tambahJumlahPenjualan(@Positive int jumlahPembelian, @NotBlank String idProduk);

    // Double jumlahHargaPerProduk(@NotBlank String idProduk);

    // Double totalKeseluruhanOmset();

    // Integer totalProdukTerjual();

    Page<OmsetResponse> findAll();

}