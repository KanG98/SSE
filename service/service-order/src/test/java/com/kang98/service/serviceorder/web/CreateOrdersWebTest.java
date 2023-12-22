package com.kang98.service.serviceorder.web;

import com.kang98.service.serviceorder.config.OrderServiceTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.kang98.service.serviceorder.config.OrderServiceTestConfig.TEST_CREATE_ORDER_INVALID_REQUEST;
import static com.kang98.service.serviceorder.config.OrderServiceTestConfig.TEST_CREATE_ORDER_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@Import(OrderServiceTestConfig.class)
public class CreateOrdersWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateOrderHttpPost_expectedSuccess() throws Exception {
        InputStream requestJson = this.getClass().getResourceAsStream(TEST_CREATE_ORDER_REQUEST);
        String JSON_STRING= new String(requestJson.readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_STRING)
                )
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateOrderHttpPost_expectedInvalidRequest() throws Exception {
        InputStream requestJson = this.getClass().getResourceAsStream(TEST_CREATE_ORDER_INVALID_REQUEST);
        String JSON_STRING= new String(requestJson.readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_STRING)
                )
                .andExpect(status().isBadRequest());
    }
}
