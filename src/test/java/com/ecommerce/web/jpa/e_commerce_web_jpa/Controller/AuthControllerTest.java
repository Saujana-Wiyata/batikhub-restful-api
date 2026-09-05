package com.ecommerce.web.jpa.e_commerce_web_jpa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthRequestId;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.auth.AuthResponse;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void testAuthStaffSuccess() throws JacksonException, Exception {

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
        void testAuthStaffFail() throws JacksonException, Exception {

                AuthRequest staff = new AuthRequest("khairy@gmail.com", " ");

                mockMvc.perform(
                                post("/api/v1/auth/login")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(staff)))
                                .andExpect(status().isBadRequest())
                                .andDo(result -> {
                                        WebResponse<AuthResponse> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<AuthResponse>>() {
                                                        });

                                        assertEquals(400, value.getCode());
                                        assertNull(value.getData());
                                        assertNotNull(value.getError());
                                });
        }

        @Test
        void testAuthEMailAndPasswordNotMatch() throws JacksonException, Exception {
                AuthRequest staff = new AuthRequest("khairy@gmail.com", "123khairy");

                mockMvc.perform(
                                post("/api/v1/auth/login")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(staff)))
                                .andExpect(status().isUnauthorized())
                                .andDo(result -> {
                                        WebResponse<AuthResponse> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<AuthResponse>>() {
                                                        });

                                        assertEquals(401, value.getCode());
                                        assertNull(value.getData());
                                        assertNotNull(value.getError());
                                });
        }

        @Test
        void testAuthById() throws JacksonException, Exception {

                AuthRequestId authId = new AuthRequestId("c0ccbe");

                mockMvc.perform(
                                post("/api/v1/auth/login-id")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(authId)))
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
        void testAuthByIdFail() throws JacksonException, Exception {

                AuthRequestId authId = new AuthRequestId(" ");

                mockMvc.perform(
                                post("/api/v1/auth/login-id")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(authId)))
                                .andExpect(status().isBadRequest())
                                .andDo(result -> {
                                        WebResponse<AuthResponse> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<AuthResponse>>() {
                                                        });

                                        assertEquals(400, value.getCode());
                                        assertNull(value.getData());
                                        assertNotNull(value.getError());
                                });
        }

        @Test
        void testAuthByIdNotFound() throws JacksonException, Exception {

                AuthRequestId authId = new AuthRequestId("123");

                mockMvc.perform(
                                post("/api/v1/auth/login-id")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(authId)))
                                .andExpect(status().isUnauthorized())
                                .andDo(result -> {
                                        WebResponse<AuthResponse> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<AuthResponse>>() {
                                                        });

                                        assertEquals(401, value.getCode());
                                        assertNull(value.getData());
                                        assertNotNull(value.getError());
                                });
        }

        @Test
        void testLogoutSuccess() throws Exception {

                mockMvc.perform(
                                delete("/api/v1/auth/logout")
                                                .header("X-API-TOKEN", "9c53fb")
                                                .accept(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk())
                                .andDo(result -> {
                                        WebResponse<AuthResponse> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<AuthResponse>>() {
                                                        });

                                        assertEquals(200, value.getCode());
                                        assertNotNull(value.getData());
                                        assertNull(value.getError());
                                        assertNull(value.getData().getToken());
                                        assertNull(value.getData().getTokenExpired());
                                });
        }

        @Test
        void testLogoutFail() throws Exception {

                mockMvc.perform(
                                delete("/api/v1/auth/logout")
                                                .header("X-API-TOKEN", "owj123")
                                                .accept(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isUnauthorized())
                                .andDo(result -> {
                                        WebResponse<AuthResponse> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<AuthResponse>>() {
                                                        });

                                        assertEquals(401, value.getCode());
                                        assertNull(value.getData());
                                        assertNotNull(value.getError());
                                });
        }
}
