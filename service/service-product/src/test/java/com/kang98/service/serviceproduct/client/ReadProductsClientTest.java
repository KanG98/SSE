package com.kang98.service.serviceproduct.client;

import com.kang98.service.serviceauth.dto.AuthRequest;
import com.kang98.service.serviceauth.dto.AuthResponse;
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
import java.net.URI;
import java.net.URISyntaxException;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReadProductsClientTest {

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

    @Test
    @Disabled // currently not working due to auth
    public void testProductAllHttpPost_expectedSuccess() throws URISyntaxException {

        final String baseUrl = "http://localhost:" + port + "/products/all";

        String token = getToken();
        System.out.println(token);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        headers.add(HttpHeaders.AUTHORIZATION, token);


        URI location = new URI(baseUrl);
        testRestTemplate.postForEntity(location, HttpEntity.EMPTY, Void.class);
        ResponseEntity<Void> response =
                testRestTemplate.postForEntity(location, HttpEntity.EMPTY, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testProductAllHttpPost_expectedForbidden() throws URISyntaxException {

        final String baseUrl = "http://localhost:" + port + "/products/all";

        URI location = new URI(baseUrl);
        ResponseEntity<Void> response =
                testRestTemplate.postForEntity(location, HttpEntity.EMPTY, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
