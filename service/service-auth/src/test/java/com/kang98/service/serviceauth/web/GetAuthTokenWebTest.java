package com.kang98.service.serviceauth.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang98.service.serviceauth.config.TestSecurityConfig;
import com.kang98.service.serviceauth.dto.AuthRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@Import(TestSecurityConfig.class)
@AutoConfigureDataJpa
public class GetAuthTokenWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${JWTUSER:sampleUser}")
    private String username;

    @Value("${JWTPWD:samplePwd}")
    private String password;

    @Test
    void testGetAuthToken_expectedOK() throws Exception {
        if (!username.equals("sampleUser") && !password.equals("samplePwd")) {
            AuthRequest testUser = AuthRequest.builder().username(username).password(password).build();
            mockMvc.perform(post("/authenticate")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(testUser))
                    )
                    .andExpect(status().isOk());
        }
    }

    @Test
    void testGetAuthToken_expectedForbidden() throws Exception {
        AuthRequest testUser = AuthRequest.builder().username("testUser").password("testUser").build();
        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testUser))
                )
                .andExpect(status().isForbidden());
    }

    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
