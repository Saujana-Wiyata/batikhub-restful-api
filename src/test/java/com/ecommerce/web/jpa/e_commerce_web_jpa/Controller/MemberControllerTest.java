package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        void testPromoPage() throws Exception {
                mockMvc.perform(
                                get("/promo")).andExpect(status().isOk());
        }

        @Test
        void testCariPage() throws Exception {
                mockMvc.perform(get("/findproduct")).andExpect(status().isOk());
        }

        @Test
        void testCariProduk() throws Exception {
                mockMvc.perform(
                                post("/findbyname")
                                                .param("produkName", "Kemeja"))
                                .andExpectAll(
                                                status().isOk(),
                                                view().name("visitor/cariPage"),
                                                model().attribute("totalProduk", 1));
        }

        @Test
        void testCariProdukZeroResult() throws Exception {
                mockMvc.perform(
                                post("/findbyname")
                                                .param("produkName", "jkhxsna"))
                                .andExpectAll(
                                                status().isOk(),
                                                view().name("visitor/cariPage"),
                                                model().attribute("totalProduk", 0));
        }

        @Test
        void testGetBuyPage() throws Exception {
                mockMvc.perform(
                                get("/buyproduct")
                                                .param("idProduk", "A02"))
                                .andExpect(
                                                status().isOk());
        }
}
