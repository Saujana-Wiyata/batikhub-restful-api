package com.ecommerce.web.jpa.e_commerce_web_jpa.model.omset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OmsetRequest {

    private Integer id;

    @Positive
    private Integer jumlahPenjualan;

    @NotBlank
    private String idProduk;

}
