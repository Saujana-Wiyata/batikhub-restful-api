package com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlamatDTO {

    @NotBlank
    private String jalan;

    @NotBlank
    private String kota;

    @NotBlank
    private String provinsi;
}
