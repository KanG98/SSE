package com.kang98.service.servicenotificationemail.client;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MailgunEmailClientTest {

    @Test
    public void testSendEmail_expectedFail() {
        MailgunEmailClient mailgunEmailClient = new MailgunEmailClient();
        assertThrows(HttpClientErrorException.class, () -> mailgunEmailClient.sendEmail("samplekey", "id"));
    }
}
