package com.kang98.foundation.security;

import com.kang98.foundation.dto.AuthRequest;
import com.kang98.foundation.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class JwtToken {

    String getToken(@Value("${JWTUSER:sampleUser}") String username, @Value("${JWTPWD:samplePwd}") String password ) throws URISyntaxException {
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
}
