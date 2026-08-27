package com.ecommerce.web.jpa.e_commerce_web_jpa.dto.produk;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Omset;
import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.Transaksi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProdukInsertDTO {

    @NotBlank
    private String id;

    @NotBlank
    private String nama;

    @Positive
    private Integer stock;

    @Positive
    private double harga;

    @NotBlank
    private String productCategory;

    private MultipartFile gambar;

    private List<Transaksi> listTransaksi;

    private Omset omset;

}
