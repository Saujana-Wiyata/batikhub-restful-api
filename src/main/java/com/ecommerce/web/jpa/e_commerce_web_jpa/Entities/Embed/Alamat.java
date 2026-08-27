package com.ecommerce.web.jpa.e_commerce_web_jpa.entities.embed;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Alamat {

    private String jalan;

    private String kota;

    private String provinsi;

}
