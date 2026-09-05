package com.ecommerce.web.jpa.e_commerce_web_jpa.model.omset;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OmsetResponse {

    private double omset;
    private int totalProductSoldout;
    private OmsetPerProduk omsetPerproduct;

}
