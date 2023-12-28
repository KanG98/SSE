package com.kang98.service.serviceorder.web;

import com.kang98.service.serviceauth.dto.AuthRequest;
import com.kang98.service.serviceauth.dto.AuthResponse;
import com.kang98.service.serviceorder.config.OrderServiceTestConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import static com.kang98.service.serviceorder.config.OrderServiceTestConfig.TEST_CREATE_ORDER_INVALID_REQUEST;
import static com.kang98.service.serviceorder.config.OrderServiceTestConfig.TEST_CREATE_ORDER_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@Import(OrderServiceTestConfig.class)
@Disabled
public class CreateOrdersWebTest {

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
    void testCreateOrderHttpPost_expectedSuccess() throws Exception {
        InputStream requestJson = this.getClass().getResourceAsStream(TEST_CREATE_ORDER_REQUEST);
        String JSON_STRING= new String(requestJson.readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(post("/orders")
                        .header("authorization", "Bearer " + getToken())
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
                        .header("authorization", "Bearer " + getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_STRING)
                )
                .andExpect(status().isBadRequest());
    }
}
