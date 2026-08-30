package com.ecommerce.web.jpa.e_commerce_web_jpa.model.member;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.embed.Alamat;

import lombok.Data;

@Data
public class MemberUpdateRequest {

    private String id;

    private String name;

    private String email;

    private String password;

    private Alamat alamat;

}
