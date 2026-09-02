package com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.enums.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdukResponse {

    private String idProduct;
    private String nama;
    private Integer stock;
    private Double harga;
    private ProductCategory productCategory;
    private byte[] gambar;

}
