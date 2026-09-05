package com.ecommerce.web.jpa.e_commerce_web_jpa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.WebResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthResponse;

import jakarta.servlet.http.Cookie;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class InterceptorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void login() throws JacksonException, Exception {
        AuthRequest staff = new AuthRequest("khairy@gmail.com", "khairy123");

        mockMvc.perform(
                post("/api/v1/auth/login-emailpassword")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(staff)))
                .andExpect(status().isOk())
                .andDo(result -> {
                    WebResponse<AuthResponse> value = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<WebResponse<AuthResponse>>() {
                            });

                    assertEquals(200, value.getCode());
                    assertNotNull(value.getData());
                    assertNull(value.getError());
                    assertNotNull(value.getData().getToken());
                    assertNotNull(value.getData().getTokenExpired());
                });
    }

    @Test
    void testHadAccess() throws Exception {
        mockMvc.perform(
                get("/api/v1/products")).andExpect(status().isOk());
    }

    @Test
    void testDontHadAccess() throws Exception {
        mockMvc.perform(
                get("/api/v1/staff/all")).andExpect(status().is3xxRedirection());
    }

    @Test
    void testLogoutSuccess() throws Exception {

        Cookie cookie = new Cookie("X-API-TOKEN", "c7eb32");

        mockMvc.perform(
                delete("/api/v1/auth/logout")
                        .header("X-API-TOKEN", "c7eb32")
                        .cookie(cookie)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(
                        status().isOk())
                .andDo(result -> {
                    WebResponse<String> value = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<WebResponse<String>>() {
                            });

                    assertEquals(200, value.getCode());
                    assertNotNull(value.getData());
                    assertNull(value.getError());
                    assertEquals("OK", value.getData());
                });
    }
}
