package com.kang98.service.servicenotificationemail.client;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

public class MailgunEmailClient {

    public void sendEmail(String apiSecret, String id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String auth = "api:" + apiSecret;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("from", "Mailgun Sandbox <yankang198@gmail.com>");
        map.add("to", "Yankang Xue <yankang198@gmail.com>");
        map.add("subject", "Hello Yankang Xue, order " + id + " has been placed!");
        map.add("text", "Congratulations Yankang Xue, you just sent an email with Mailgun!  You are truly awesome!");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("https://api.mailgun.net/v3/sandbox95577af60d0947f6b7e0a2fddf4cd942.mailgun.org/messages", request, String.class);

        System.out.println(response.getBody());
    }
}
