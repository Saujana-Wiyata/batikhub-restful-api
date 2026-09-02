package com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdukUpdateRequest {

    private String nama;

    private Integer stock;

    private Double harga;

    private String productCategory;

    private MultipartFile gambar;
}
