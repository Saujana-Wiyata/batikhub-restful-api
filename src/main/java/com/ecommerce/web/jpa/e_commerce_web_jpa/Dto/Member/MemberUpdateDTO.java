package com.ecommerce.web.jpa.e_commerce_web_jpa.Dto.Member;

import com.ecommerce.web.jpa.e_commerce_web_jpa.Entities.Embed.Alamat;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class MemberUpdateDTO {

    private String id;

    private String name;

    private String email;

    private String password;

    @Valid
    private Alamat alamat;

}
