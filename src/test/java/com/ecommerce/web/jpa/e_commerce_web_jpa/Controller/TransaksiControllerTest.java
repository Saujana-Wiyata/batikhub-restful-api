package com.ecommerce.web.jpa.e_commerce_web_jpa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.transaksi.TransaksiInsertRequest;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.transaksi.TransaksiResponse;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TransaksiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testInsertSuccess() throws JacksonException, Exception {

        TransaksiInsertRequest createTransaksi = new TransaksiInsertRequest(2,
                "48e83a", "BTK-123-K");

        mockMvc.perform(
                post("/api/v1/transaction")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(createTransaksi)))
                .andExpect(status().isOk())
                .andDo(result -> {
                    WebResponse<String> value = objectMapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<WebResponse<String>>() {
                            });

                    assertEquals(200, value.getCode());
                    assertNotNull(value.getData());
                    assertNull(value.getError());
                });
    }

    @Test
    void testInsertFail() throws JacksonException, Exception {

        TransaksiInsertRequest createTransaksi = new TransaksiInsertRequest(-2,
                " ", " ");

        mockMvc.perform(
                post("/api/v1/transaction")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(createTransaksi)))
                .andExpect(status().isBadRequest())
                .andDo(result -> {
                    WebResponse<String> value = objectMapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<WebResponse<String>>() {
                            });

                    assertEquals(400, value.getCode());
                    assertNull(value.getData());
                    assertNotNull(value.getError());
                });
    }

    @Test
    void testFindAllTransaksi() throws Exception {

        mockMvc.perform(
                get("/api/v1/transaction/all")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(result -> {
                    WebResponse<List<TransaksiResponse>> value = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<WebResponse<List<TransaksiResponse>>>() {
                            });

                    assertEquals(200, value.getCode());
                    assertNotNull(value.getData());
                    assertNull(value.getError());
                    assertNotNull(value.getPaging());
                    assertEquals(1, value.getPaging().getTotalElements());
                });
    }

    @Test
    void testDeleteFail() throws Exception {
        mockMvc.perform(
                delete("/api/v1/transaction/{id}", "123")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andDo(result -> {
                    WebResponse<String> value = objectMapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<WebResponse<String>>() {
                            });

                    assertEquals(400, value.getCode());
                    assertNull(value.getData());
                    assertNotNull(value.getError());
                });
    }

    @Test
    void testDeleteSuccess() throws Exception {
        mockMvc.perform(
                delete("/api/v1/transaction/{id}", "8")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(result -> {
                    WebResponse<String> value = objectMapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<WebResponse<String>>() {
                            });

                    assertEquals(200, value.getCode());
                    assertNotNull(value.getData());
                    assertNull(value.getError());
                });
    }
}
