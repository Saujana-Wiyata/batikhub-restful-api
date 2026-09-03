package com.ecommerce.web.jpa.e_commerce_web_jpa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ecommerce.web.jpa.e_commerce_web_jpa.model.WebResponse;
import com.ecommerce.web.jpa.e_commerce_web_jpa.model.omset.OmsetResponse;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class OmsetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testDashboard() throws Exception {

        mockMvc.perform(
                get("/api/v1/omset/dashboard")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(result -> {
                    WebResponse<List<OmsetResponse>> value = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<WebResponse<List<OmsetResponse>>>() {
                            });

                    assertEquals(200, value.getCode());
                    assertNotNull(value.getData());
                    assertNull(value.getError());
                    assertNotNull(value.getPaging());

                    for (OmsetResponse t : value.getData()) {

                        assertEquals((7 * 13.46) + (10.69 * 5), t.getOmset());
                        assertEquals(7 + 5, t.getTotalProductSoldout());

                        System.out.println("id produk : " + t.getOmsetPerproduct().getIdProduct());
                        System.out.println("nama produk : " + t.getOmsetPerproduct().getProductName());
                        System.out.println("omset per produk : " + t.getOmsetPerproduct().getOmsetPerProduk());
                        System.out.println("produk terjual : " + t.getOmsetPerproduct().getTotalItemsSoldOut());
                        System.out.println("-----------------------------------------");
                    }

                });
    }
}
