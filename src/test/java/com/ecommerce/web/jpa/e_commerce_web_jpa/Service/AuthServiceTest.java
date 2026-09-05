package com.ecommerce.web.jpa.e_commerce_web_jpa.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.service.auth.AuthService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Test
    void testMemberLoginByEmailPasswordSuccess() {
        AuthResponse member = authService.loginByEmailAndPassword(
                new AuthRequest("syakirlongoi@gmail.com", "syakir123"));

        assertNotNull(member.getToken());
        assertNotNull(member.getTokenExpired());
    }

    @Test
    void testMemberLoginByEmailPasswordBlank() {
        assertThrows(ConstraintViolationException.class, () -> {
            authService.loginByEmailAndPassword(
                    new AuthRequest("syakirlongoi@gmail.com", ""));
        });
    }

    @Test
    void testMemberLoginByEmailPasswordNotMatch() {
        assertThrows(ResponseStatusException.class, () -> {
            authService.loginByEmailAndPassword(
                    new AuthRequest("syakirlongoi@gmail.com", "123rahasia"));
        });
    }

    @Test
    void testStaffLoginByEmailPasswordSuccess() {
        AuthResponse result = authService.loginByEmailAndPassword(
                new AuthRequest("khairy@gmail.com", "khairy123"));

        assertNotNull(result.getToken());
        assertNotNull(result.getTokenExpired());
    }

    @Test
    void testLogoutStaffSuccess() {
        authService.logout("3c3bbd");
    }

    @Test
    void testLogoutStaffFail() {
        assertThrows(ResponseStatusException.class, () -> {
            authService.logout("123");
        });
    }

    @Test
    void testLogoutMemberSuccess() {
        authService.logout("48e83a");
    }

    @Test
    void testLogoutMemberFail() {
        assertThrows(ResponseStatusException.class, () -> {
            authService.logout("321");
        });
    }

    @Test
    void testLoginById() {
        AuthResponse loginById = authService.loginById("c0ccbe");

        assertNotNull(loginById.getToken());
        assertNotNull(loginById.getTokenExpired());
    }

    @Test
    void testLoginByIdFail() {

        assertThrows(ResponseStatusException.class, () -> {
            authService.loginById("abc123");
        });
    }

    @Test
    void testLoginByIdBlank() {

        assertThrows(ConstraintViolationException.class, () -> {
            authService.loginById(" ");
        });
    }
}
