package com.ecommerce.web.jpa.e_commerce_web_jpa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.WebResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.staff.StaffUpdateRequest;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class StaffControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void testInsertSuccess() throws JacksonException, Exception {

                StaffRequest newStaff = new StaffRequest("Taufiq", "topek@gmail.com", "topek123", "STAFF");

                mockMvc.perform(
                                post("/api/v1/staff")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(newStaff)))
                                .andExpect(status().isOk())
                                .andDo(result -> {
                                        WebResponse<String> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<String>>() {
                                                        });

                                        assertEquals(200, value.getCode());
                                        assertNull(value.getError());
                                        assertNotNull(value.getData());
                                });
        }

        @Test
        void testInsertFail() throws JacksonException, Exception {

                StaffRequest newStaff = new StaffRequest("Taufiq", "topek.com", "topek123", "STAFF");

                mockMvc.perform(
                                post("/api/v1/staff")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(newStaff)))
                                .andExpect(status().isBadRequest())
                                .andDo(result -> {
                                        WebResponse<String> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<String>>() {
                                                        });

                                        assertEquals(400, value.getCode());
                                        assertNotNull(value.getError());
                                        assertNull(value.getData());
                                });
        }

        @Test
        void testUpdateSuccess() throws JacksonException, Exception {

                StaffUpdateRequest staffUpdate = new StaffUpdateRequest("", "topeklongoi@gmail.com", "", "");

                mockMvc.perform(
                                patch("/api/v1/staff/current")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(staffUpdate))
                                                .header("token", "849ae9"))
                                .andExpect(status().isOk())
                                .andDo(result -> {
                                        WebResponse<StaffResponse> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<StaffResponse>>() {
                                                        });

                                        assertEquals(200, value.getCode());
                                        assertNotNull(value.getData());
                                        assertNull(value.getError());
                                });
        }

        @Test
        void testUpdateFail() throws JacksonException, Exception {

                StaffUpdateRequest staffUpdate = new StaffUpdateRequest("", "topeklongoi@gmail.com", "", "");

                mockMvc.perform(
                                patch("/api/v1/staff/current")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(staffUpdate))
                                                .header("token", "123"))
                                .andExpect(status().isBadRequest())
                                .andDo(result -> {
                                        WebResponse<StaffResponse> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<StaffResponse>>() {
                                                        });

                                        assertEquals(400, value.getCode());
                                        assertNull(value.getData());
                                        assertNotNull(value.getError());
                                });
        }

        @Test
        void testUpdateWrongFormatEmail() throws JacksonException, Exception {

                StaffUpdateRequest staffUpdate = new StaffUpdateRequest("", "topeklongoi.com", "", "");

                mockMvc.perform(
                                patch("/api/v1/staff/current")
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                .content(objectMapper.writeValueAsString(staffUpdate))
                                                .header("token", "849ae9"))
                                .andExpect(status().isBadRequest())
                                .andDo(result -> {
                                        WebResponse<StaffResponse> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<StaffResponse>>() {
                                                        });

                                        assertEquals(400, value.getCode());
                                        assertNull(value.getData());
                                        assertNotNull(value.getError());
                                });
        }

        @Test
        void testGetAllStaff() throws Exception {

                mockMvc.perform(
                                get("/api/v1/staff/all")).andExpect(status().isOk())
                                .andDo(result -> {
                                        WebResponse<List<StaffResponse>> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<List<StaffResponse>>>() {
                                                        });

                                        assertEquals(200, value.getCode());
                                        assertEquals(1, value.getPaging().getTotalElements());
                                        assertNull(value.getError());
                                });
        }

        @Test
        void testDeleteFail() throws Exception {

                mockMvc.perform(
                                delete("/api/v1/staff/current")
                                                .header("token", "123"))
                                .andExpect(status().isUnauthorized())
                                .andDo(result -> {
                                        WebResponse<String> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<String>>() {
                                                        });

                                        assertEquals(401, value.getCode());
                                        assertNull(value.getData());
                                        assertNotNull(value.getError());
                                });
        }

        @Test
        void testDeleteSuccess() throws Exception {
                mockMvc.perform(
                                delete("/api/v1/staff/current")
                                                .header("token", "849ae9"))
                                .andExpect(status().isOk())
                                .andDo(result -> {
                                        WebResponse<String> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<String>>() {
                                                        });

                                        assertEquals(200, value.getCode());
                                        assertNotNull(value.getData());
                                        assertNull(value.getError());
                                });
        }
}
