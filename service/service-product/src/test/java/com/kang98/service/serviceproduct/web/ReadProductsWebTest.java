package com.kang98.service.serviceproduct.web;

import com.kang98.service.serviceauth.dto.AuthRequest;
import com.kang98.service.serviceauth.dto.AuthResponse;
import com.kang98.service.serviceproduct.config.ProductServiceTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ProductServiceTestConfig.class})
@WebMvcTest
class ReadProductsWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${JWTUSER:sampleUser}")
    private String username;

    @Value("${JWTPWD:samplePwd}")
    private String password;

    String getToken() throws URISyntaxException {
        System.out.println("username" + username);
        System.out.println("pwd" + password);
        String token = "";
        RestTemplate restTemplate = new RestTemplate();
        if (!username.equals("sampleUser") && !password.equals("samplePwd")) {
            final String baseUrl = "http://localhost:" + "50231" + "/authenticate";

            AuthRequest request = AuthRequest.builder().username(username).password(password).build();
            URI location = new URI(baseUrl);
            ResponseEntity<AuthResponse> response =
                    restTemplate.postForEntity(location, request, AuthResponse.class);
            token = response.getBody().getJwt_token();
        }
        return token;
    }

    @Test
    void testGetAllProductsHttpPost_expectedSuccess() throws Exception {
        System.out.println("Token: " + getToken());
        mockMvc.perform(post("/products/all")
                .header("authorization", "Bearer " + getToken()))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllProductsHttpPost_expectedForbidden() throws Exception {
        mockMvc.perform(post("/products/all"))
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetProductsByNameHttpPost_expectedOK() throws Exception {
        System.out.println("Token: " + getToken());
        String requestBody = "{\"productName\": \"smart\"}";
        mockMvc.perform(post("/products/by-name")
                        .header("authorization", "Bearer " + getToken())
                        .content(requestBody)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
    @Test
    void testGetProductsByNameHttpPost_expectedInvalid() throws Exception {
        System.out.println("Token: " + getToken());
        mockMvc.perform(post("/products/by-name")
                        .header("authorization", "Bearer " + getToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllProductsByNameHttpPost_expectedForbidden() throws Exception {
        mockMvc.perform(post("/products/by-name"))
                .andExpect(status().isForbidden());
    }
}
