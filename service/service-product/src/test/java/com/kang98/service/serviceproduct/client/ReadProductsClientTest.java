package com.kang98.service.serviceproduct.client;

import com.kang98.foundation.security.JwtToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReadProductsClientTest {

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

    @Test
    void testProductAllHttpPost_expectedSuccess() throws URISyntaxException {
        final String baseUrl = "http://localhost:" + port + "/products/all";

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
    void testProductAllHttpPost_expectedForbidden() throws URISyntaxException {

        final String baseUrl = "http://localhost:" + port + "/products/all";

        URI location = new URI(baseUrl);
        ResponseEntity<Void> response =
                testRestTemplate.postForEntity(location, HttpEntity.EMPTY, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
