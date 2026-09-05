package com.ecommerce.web.jpa.e_commerce_web_jpa.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.WebResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthRequestId;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.auth.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private void loginHeader(AuthResponse authResponse) {

        ResponseCookie.from("X-API-TOKEN", authResponse.getToken()
                .substring(0, 6))
                .httpOnly(true) // mencegah scripting (hacking)
                .path("/")
                .maxAge(Long.parseLong(authResponse.getTokenExpired()))
                .build();
    }

    private void logoutHeader(AuthResponse authResponse) {

        ResponseCookie.from("X-API-TOKEN", null)
                .httpOnly(true) // mencegah scripting (hacking)
                .path("/")
                .maxAge(0)
                .build();
    }

    @PostMapping(path = "/api/v1/auth/login-emailpassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<AuthResponse> loginByEmailAndPassword(@RequestBody AuthRequest authRequest) {

        AuthResponse login = authService.loginByEmailAndPassword(authRequest);
        loginHeader(login);
        return WebResponse.<AuthResponse>builder()
                .code(HttpStatus.OK.value())
                .data(login)
                .build();
    }

    @PostMapping(path = "/api/v1/auth/login-id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<AuthResponse> loginById(@RequestBody AuthRequestId authId) {

        AuthResponse login = authService.loginById(authId.getId());
        loginHeader(login);
        return WebResponse.<AuthResponse>builder()
                .code(HttpStatus.OK.value())
                .data(login)
                .build();
    }

    @DeleteMapping(path = "/api/v1/auth/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<String> logout(@RequestHeader(name = "X-API-TOKEN") String token) {

        AuthResponse logout = authService.logout(token);
        logoutHeader(logout);
        return WebResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .data("OK")
                .build();
    }
}
