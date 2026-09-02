package com.ecommerce.web.jpa.e_commerce_web_jpa.model.transaksi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransaksiInsertRequest {

    @Positive
    private Integer totalPembelian;

    @NotBlank
    private String tokenMember;

    @NotBlank
    private String idProduk;
}
