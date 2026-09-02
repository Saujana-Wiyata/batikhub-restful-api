package com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdukInsertRequest {

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

}
