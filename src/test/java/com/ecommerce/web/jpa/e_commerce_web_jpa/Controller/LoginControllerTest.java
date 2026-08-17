package com.ecommerce.web.jpa.e_commerce_web_jpa.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

// import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testLoginEmailAndPassword() throws Exception {
        mockMvc.perform(
                get("/login")).andExpect(
                        status().isOk());
    }

    @Test
    void testPostLoginEmailAndPassword() throws Exception {
        mockMvc.perform(
                post("/loginemailandpassword")
                        .param("email", "taufiq@gmail.com")
                        .param("password", "taufiq123"))
                .andExpectAll(
                        status().is3xxRedirection(),
                        request().sessionAttribute("idMember", "80a542"));
    }
}