package com.ecommerce.web.jpa.e_commerce_web_jpa.model.member;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.embed.Alamat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MemberResponse {

    private String name;

    private String email;

    private Alamat alamat;

}
