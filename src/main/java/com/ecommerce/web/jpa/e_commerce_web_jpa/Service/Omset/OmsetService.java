package com.ecommerce.web.jpa.e_commerce_web_jpa.service.omset;

import java.util.List;

import com.ecommerce.web.jpa.e_commerce_web_jpa.dto.omset.OmsetDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Omset;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public interface OmsetService {

    void insert(@Valid OmsetDTO omset);

    // TODO: delete thie method because we dont use it in controller
    void delete(@Positive int id);

    void tambahJumlahPenjualan(@Positive int jumlahPembelian, @NotBlank String idProduk);

    Double jumlahHargaPerProduk(@NotBlank String idProduk);

    Double totalKeseluruhanOmset();

    Integer totalProdukTerjual();

    List<Omset> findAll();

}