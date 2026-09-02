package com.ecommerce.web.jpa.e_commerce_web_jpa.model.transaksi;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransaksiResponse {

    private Integer id;

    private Integer totalPembelian;

    private LocalDate purchaseDate;

    private LocalDate arrivalDate;

    private String idMember;

    private String idProduk;

}
