package com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Transaksi;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransaksiInsertDTO {

    private Integer id;

    @Positive
    private Integer totalPembelian;

    @PastOrPresent
    private LocalDate purchaseDate;

    @Future
    private LocalDate arrivalDate;

    @NotBlank
    private String idMember;

    @NotBlank
    private String idProduk;
}
