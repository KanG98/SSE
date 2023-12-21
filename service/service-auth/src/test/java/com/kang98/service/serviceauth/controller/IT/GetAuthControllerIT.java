package com.kang98.service.serviceauth.controller.IT;

import com.kang98.service.serviceauth.config.TestSecurityConfig;
import com.kang98.service.serviceauth.controller.GetAuthController;
import com.kang98.service.serviceauth.dto.AuthRequest;
import com.kang98.service.serviceauth.dto.AuthResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes={TestSecurityConfig.class})
public class GetAuthControllerIT {

    @Autowired
    private GetAuthController getAuthController;

    @Value("${JWTUSER}")
    private String username;

    @Value("${JWTPWD}")
    private String password;

    @Test
    void getJwtToken_authenticateAndGetToken_expectedNonNullToken() throws IOException {
        System.out.println(username + password);
        AuthRequest authRequest = AuthRequest.builder().username(username).password(password).build();
        var actual = getAuthController.authenticateAndGetToken(authRequest);

        System.out.println(actual);
        assertAll("Get all products",
                () -> assertNotNull(actual),
                () -> assertEquals(actual.getClass(), AuthResponse.class)
        );
    }

}

