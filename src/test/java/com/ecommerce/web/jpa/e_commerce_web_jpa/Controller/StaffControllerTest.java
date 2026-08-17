package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class StaffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetDashboard() throws Exception {
        mockMvc.perform(
                get("/dashboard")).andExpect(
                        status().isOk());
    }

    @Test
    void testGetAddProduk() throws Exception {
        mockMvc.perform(
                get("/addproduk")).andExpect(
                        status().isOk());
    }
}
