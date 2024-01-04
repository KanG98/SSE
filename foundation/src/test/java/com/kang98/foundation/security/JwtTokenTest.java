package com.kang98.foundation.security;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class JwtTokenTest {

    private final String username = System.getProperty("JWTUSER");
    private final String password = System.getProperty("JWTPWD");

//    need to link with vault
    @Test
    void testProductAllHttpPost_expectedSuccess() throws URISyntaxException {
        JwtToken jwtToken = new JwtToken();
        String token = jwtToken.getToken(username, password);
        assertThat(token).isNotNull();
        assertThat(token.length() > 10);
    }
}
