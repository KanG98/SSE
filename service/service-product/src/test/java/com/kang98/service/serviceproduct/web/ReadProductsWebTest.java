package com.kang98.service.serviceproduct.web;

import com.kang98.service.serviceproduct.config.ProductServiceTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ReadProductsWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateOrderHttpPost_expectedSuccess() throws Exception {
        mockMvc.perform(post("/products/all"))
                .andExpect(status().isOk());
    }
}
