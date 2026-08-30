package com.ecommerce.web.jpa.e_commerce_web_jpa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.web.jpa.e_commerce_web_jpa.entities.embed.Alamat;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.WebResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.member.AlamatInsert;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.member.MemberRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.member.MemberResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.member.MemberUpdateRequest;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void testInsertSuccess() throws JacksonException, Exception {

                MemberRequest memberRequest = new MemberRequest();
                memberRequest.setName("Syakir");
                memberRequest.setEmail("syakirlongoi@gmail.com");
                memberRequest.setPassword("syakir123");
                memberRequest.setAlamatDto(new AlamatInsert("jalan buntu",
                                "Johor Bahru", "johor"));

                mockMvc.perform(
                                post("/api/v1/member")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(memberRequest)))
                                .andExpect(status().isOk())
                                .andDo(result -> {
                                        WebResponse<String> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<String>>() {
                                                        });

                                        assertEquals(200, value.getCode());
                                        assertEquals("OK", value.getData());
                                });
        }

        @Test
        void testInsertFail() throws JacksonException, Exception {

                MemberRequest memberRequest = new MemberRequest();
                memberRequest.setName("Syakir");
                memberRequest.setEmail("syakirlongoi@gmail.com");
                memberRequest.setPassword("syakir123");
                memberRequest.setAlamatDto(new AlamatInsert(" ",
                                "Johor Bahru", "johor"));

                mockMvc.perform(
                                post("/api/v1/member")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(memberRequest)))
                                .andExpect(status().isBadRequest())
                                .andDo(result -> {
                                        WebResponse<String> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<String>>() {
                                                        });

                                        assertNotEquals(200, value.getCode());
                                        assertNotNull(value.getError());
                                        System.out.println(value.getError());
                                });
        }

        @Test
        void testUpdateSuccess() throws JacksonException, Exception {

                MemberUpdateRequest member = new MemberUpdateRequest();
                member.setName("");
                member.setEmail("");
                member.setPassword("syakirlongoi123");
                member.setAlamat(new Alamat("", "", ""));

                mockMvc.perform(
                                patch("/api/v1/member/current")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(member))
                                                .header("token", "e9804b"))
                                .andExpect(status().isOk())
                                .andDo(result -> {
                                        WebResponse<MemberResponse> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<MemberResponse>>() {
                                                        });

                                        assertNull(value.getError());
                                        assertEquals(200, value.getCode());
                                        assertNotNull(value.getData());
                                });
        }

        @Test
        void testUpdateTokenNotFound() throws JacksonException, Exception {

                MemberUpdateRequest member = new MemberUpdateRequest();
                member.setName("");
                member.setEmail("");
                member.setPassword("syakirlongoi123");
                member.setAlamat(new Alamat("", "", ""));

                mockMvc.perform(
                                patch("/api/v1/member/current")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(member))
                                                .header("token", "123"))
                                .andExpect(status().isBadRequest())
                                .andDo(result -> {
                                        WebResponse<MemberResponse> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<MemberResponse>>() {
                                                        });

                                        assertNull(value.getData());
                                        assertEquals(400, value.getCode());
                                        assertNotNull(value.getError());
                                });
        }
}
