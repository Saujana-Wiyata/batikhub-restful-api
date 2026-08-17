package com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Member;

import java.util.List;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Transaksi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberInputDTO {

    private String id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @Valid
    private AlamatDTO alamatDto;

    private List<Transaksi> listTransaksi;

}
