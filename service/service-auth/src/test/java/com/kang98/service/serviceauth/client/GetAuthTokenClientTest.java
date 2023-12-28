package com.kang98.service.serviceauth.client;

import com.kang98.service.serviceauth.dto.AuthRequest;
import com.kang98.service.serviceauth.dto.AuthResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetAuthTokenClientTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Value("${JWTUSER:sampleUser}")
    private String username;

    @Value("${JWTPWD:samplePwd}")
    private String password;

    @Test
    public void testGetAuthenticationHttpPost_expectedOK() throws URISyntaxException {

        if (!username.equals("sampleUser") && !password.equals("samplePwd")) {
            final String baseUrl = "http://localhost:" + port + "/authenticate";

            AuthRequest request = AuthRequest.builder().username(username).password(password).build();
            URI location = new URI(baseUrl);
            ResponseEntity<AuthResponse> response =
                    testRestTemplate.postForEntity(location, request, AuthResponse.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
    }

    @Test
    public void testGetAuthenticationHttpPost_expectedForbidden() throws URISyntaxException {

        final String baseUrl = "http://localhost:" + port + "/authenticate";

        AuthRequest request = AuthRequest.builder().username("test").password("test").build();
        URI location = new URI(baseUrl);
        ResponseEntity<AuthResponse> response =
                testRestTemplate.postForEntity(location, request, AuthResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
