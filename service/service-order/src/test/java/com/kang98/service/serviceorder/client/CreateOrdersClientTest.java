package com.kang98.service.serviceorder.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang98.foundation.security.JwtToken;
import com.kang98.service.serviceorder.dto.CreateOrdersRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import static com.kang98.service.serviceorder.config.OrderServiceTestConfig.TEST_CREATE_ORDER_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateOrdersClientTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private JwtToken jwtToken;

    @LocalServerPort
    private int port;

    @Value("${JWTUSER:sampleUser}")
    private String username;

    @Value("${JWTPWD:samplePwd}")
    private String password;

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

        String token = jwtToken.getToken(username, password);
        HttpEntity<CreateOrdersRequest> entity = getAuthEntity(token, createOrdersRequest);

        ResponseEntity<Void> response =
                testRestTemplate.postForEntity(location, entity, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void testCreateOrderHttpPost_expectedInvalidRequest() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/orders";

        CreateOrdersRequest createOrdersRequest = CreateOrdersRequest.builder().build();

        String token = jwtToken.getToken(username, password);
        HttpEntity<CreateOrdersRequest> entity = getAuthEntity(token, createOrdersRequest);

        URI location = new URI(baseUrl);
        ResponseEntity<Void> response =
                testRestTemplate.postForEntity(location, entity, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testGetOrdersHttpPost_expectedSuccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/orders/all";

        URI location = new URI(baseUrl);

        String token = jwtToken.getToken(username, password);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(null, headers);

        ResponseEntity<Void> response =
                testRestTemplate.postForEntity(location, entity, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testGetOrdersHttpPost_expectedForbidden() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/orders/all";

        URI location = new URI(baseUrl);

        HttpEntity<Void> entity = new HttpEntity<>(null, null);

        ResponseEntity<Void> response =
                testRestTemplate.postForEntity(location, entity, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

}
