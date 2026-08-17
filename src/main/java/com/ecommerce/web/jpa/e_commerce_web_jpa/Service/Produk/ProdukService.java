package com.ecommerce.web.jpa.e_commerce_web_jpa.Service.Produk;

import java.util.List;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Produk.ProdukInsertDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Produk.ProdukUpdateDTO;
import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Produk;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public interface ProdukService {

    void insert(@Valid ProdukInsertDTO produk);

    Produk findById(@NotBlank String id);

    void kurangiStock(@Positive int jmlhPembelian, @NotBlank String idProduk);

    List<Produk> findByNama(@NotBlank String nameProduk);

    List<Produk> findAll();

    Produk update(@NotBlank String id, ProdukUpdateDTO produk);

    void delete(@NotBlank String id);

}
