package com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
