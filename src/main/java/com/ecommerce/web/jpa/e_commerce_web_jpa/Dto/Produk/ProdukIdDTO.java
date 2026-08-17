package com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Produk;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProdukIdDTO {

    @NotBlank
    private String id;
}
