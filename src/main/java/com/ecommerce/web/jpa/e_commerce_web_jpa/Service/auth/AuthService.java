package com.ecommerce.web.jpa.e_commerce_web_jpa.service.auth;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthResponse;

import jakarta.validation.Valid;

public interface AuthService {

    AuthResponse loginByEmailAndPassword(@Valid AuthRequest authRequest);

    AuthResponse loginById(String id);

    void logout(String token);

}
