package com.ecommerce.web.jpa.e_commerce_web_jpa.model.omset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OmsetPerProduk {

    private String idProduct;
    private String productName;
    private double omsetPerProduk;
    private int totalItemsSoldOut;

}
