package com.kang98.service.serviceorder.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang98.service.serviceauth.dto.AuthRequest;
import com.kang98.service.serviceauth.dto.AuthResponse;
import com.kang98.service.serviceorder.dto.CreateOrdersRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import static com.kang98.service.serviceorder.config.OrderServiceTestConfig.TEST_CREATE_ORDER_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Disabled
public class CreateOrdersClientTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

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

    HttpEntity<CreateOrdersRequest> getAuthEntity(String token, CreateOrdersRequest createOrdersRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+token);

        HttpEntity<CreateOrdersRequest> entity = new HttpEntity<>(createOrdersRequest, headers);
        return entity;
    }

    @Test
    void testCreateOrderHttpPost_expectedSuccess() throws IOException, URISyntaxException {
        InputStream requestJson = this.getClass().getResourceAsStream(TEST_CREATE_ORDER_REQUEST);

        final String baseUrl = "http://localhost:" + port + "/orders";

        String JSON_STRING= new String(requestJson.readAllBytes(), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        CreateOrdersRequest createOrdersRequest = mapper.readValue(JSON_STRING, CreateOrdersRequest.class);

        URI location = new URI(baseUrl);

        String token = getToken();
        HttpEntity<CreateOrdersRequest> entity = getAuthEntity(token, createOrdersRequest);

        ResponseEntity<Void> response =
                testRestTemplate.postForEntity(location, entity, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void testCreateOrderHttpPost_expectedInvalidRequest() throws IOException, URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/orders";

        CreateOrdersRequest createOrdersRequest = CreateOrdersRequest.builder().build();

        String token = getToken();
        HttpEntity<CreateOrdersRequest> entity = getAuthEntity(token, createOrdersRequest);

        URI location = new URI(baseUrl);
        ResponseEntity<Void> response =
                testRestTemplate.postForEntity(location, entity, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

}
