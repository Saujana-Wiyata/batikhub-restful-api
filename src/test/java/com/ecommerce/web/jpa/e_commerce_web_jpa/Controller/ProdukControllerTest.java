package com.ecommerce.web.jpa.e_commerce_web_jpa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.WebResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.produk.ProdukResponse;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdukControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void testInsertSuccess() throws JacksonException, Exception {

                Path of = Path.of("batikhub-erd.png");
                byte[] allBytes = Files.readAllBytes(of);

                MultipartFile multipartFile = new MockMultipartFile(
                                "gambar", // nama field
                                "batikhub-erd.png", // nama file asli
                                "image/png", // tipe file
                                allBytes); // byteFile

                mockMvc.perform(
                                multipart("/api/v1/products")
                                                .file((MockMultipartFile) multipartFile)
                                                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .param("id", "BTK-631-KL")
                                                .param("nama", "Kain Motif Parang")
                                                .param("stock", "10")
                                                .param("harga", "15.76")
                                                .param("productCategory", "KAIN"))
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

                Path of = Path.of("batikhub-erd.png");
                byte[] allBytes = Files.readAllBytes(of);

                MultipartFile multipartFile = new MockMultipartFile(
                                "gambar", // nama field
                                "batikhub-erd.png", // nama file asli
                                "image/png", // tipe file
                                allBytes); // byteFile

                mockMvc.perform(
                                multipart("/api/v1/products")
                                                .file((MockMultipartFile) multipartFile)
                                                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .param("id", "BTK-631-KL")
                                                .param("nama", " ") // blank name
                                                .param("stock", "10")
                                                .param("harga", "15.76")
                                                .param("productCategory", "KAIN"))
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
        void testUpdateSuccess() throws Exception {

                mockMvc.perform(
                                patch("/api/v1/products/{id}", "BTK-631-KL")
                                                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .param("nama", "")
                                                .param("stock", "3")
                                                .param("harga", "9.0")
                                                .param("productCategory", ""))
                                .andExpect(status().isOk())
                                .andDo(result -> {
                                        WebResponse<ProdukResponse> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<ProdukResponse>>() {
                                                        });

                                        assertEquals(200, value.getCode());
                                        assertNotNull(value.getData());
                                        assertNull(value.getError());
                                        assertEquals(13, value.getData().getStock());
                                });
        }

        @Test
        void testUpdateFail() throws Exception {

                mockMvc.perform(
                                patch("/api/v1/products/{id}", "BTK-631-KL")
                                                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                                                .accept(MediaType.APPLICATION_JSON_VALUE)
                                                .param("nama", "")
                                                .param("stock", "0")
                                                .param("harga", "-9.0")
                                                .param("productCategory", "S"))
                                .andExpect(status().isBadRequest())
                                .andDo(result -> {
                                        WebResponse<ProdukResponse> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<ProdukResponse>>() {
                                                        });

                                        assertEquals(400, value.getCode());
                                        assertNull(value.getData());
                                        assertNotNull(value.getError());
                                });
        }

        @Test
        void testGetAllProducts() throws JacksonException, Exception {
                mockMvc.perform(
                                get("/api/v1/products")).andExpect(status().isOk())
                                .andDo(result -> {
                                        WebResponse<List<ProdukResponse>> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<List<ProdukResponse>>>() {
                                                        });
                                        assertEquals(200, value.getCode());
                                        assertNotNull(value.getData());
                                        assertNull(value.getError());
                                        assertNotNull(value.getPaging());
                                        assertEquals(1, value.getPaging().getTotalElements());
                                });
        }

        @Test
        void testFindProductByNameFound() throws Exception {

                mockMvc.perform(
                                get("/api/v1/products/search")
                                                .param("name", "Motif")
                                                .accept(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk())
                                .andDo(result -> {
                                        WebResponse<List<ProdukResponse>> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<List<ProdukResponse>>>() {
                                                        });

                                        assertEquals(200, value.getCode());
                                        assertEquals(1, value.getPaging().getTotalElements());
                                        assertNotNull(value.getData());
                                        assertNull(value.getError());
                                });
        }

        @Test
        void testFindProductByNameNotFound() throws Exception {

                mockMvc.perform(
                                get("/api/v1/products/search")
                                                .param("name", "Sepatu")
                                                .accept(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk())
                                .andDo(result -> {
                                        WebResponse<List<ProdukResponse>> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
                                                        new TypeReference<WebResponse<List<ProdukResponse>>>() {
                                                        });

                                        assertEquals(200, value.getCode());
                                        assertEquals(0, value.getPaging().getTotalElements());
                                        assertNotNull(value.getData());
                                        assertNull(value.getError());
                                });
        }

        @Test
        void testDeleteFail() throws Exception {

                mockMvc.perform(
                                delete("/api/v1/products/{id}", "1234")
                                                .accept(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isBadRequest())
                                .andDo(result -> {
                                        WebResponse<String> value = objectMapper.readValue(
                                                        result.getResponse().getContentAsString(),
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
                                delete("/api/v1/products/{id}", "BTK-631-KL")
                                                .accept(MediaType.APPLICATION_JSON_VALUE))
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
