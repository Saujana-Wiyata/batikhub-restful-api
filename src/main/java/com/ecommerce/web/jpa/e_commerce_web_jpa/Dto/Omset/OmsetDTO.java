package com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Omset;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Produk.ProdukIdDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OmsetDTO {

    private Integer id;

    @Positive
    private Integer jumlahPenjualan;

    @Valid
    private ProdukIdDTO idProduk;

}
