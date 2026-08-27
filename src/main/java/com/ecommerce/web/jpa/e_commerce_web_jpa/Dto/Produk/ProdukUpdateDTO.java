package com.ecommerce.web.jpa.e_commerce_web_jpa.dto.produk;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProdukUpdateDTO {

    private String id;

    private String nama;

    private Integer stock;

    private Double harga;

    private String productCategory;

    private MultipartFile gambar;
}
