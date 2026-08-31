package com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.enums.Role;

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
public class StaffResponse {

    private String name;

    private String email;

    private Role role;

}
