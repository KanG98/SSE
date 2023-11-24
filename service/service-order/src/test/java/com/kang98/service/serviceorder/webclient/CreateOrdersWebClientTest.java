package com.kang98.service.serviceorder.webclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang98.service.serviceorder.dto.CreateOrdersRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.kang98.service.serviceorder.config.OrderServiceTestConfig.TEST_CREATE_ORDER_INVALID_REQUEST;
import static com.kang98.service.serviceorder.config.OrderServiceTestConfig.TEST_CREATE_ORDER_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateOrdersWebClientTest {

    @Test
    public void testCreateOrderHttpPost_expectedSuccess() throws IOException {
        InputStream requestJson = this.getClass().getResourceAsStream(TEST_CREATE_ORDER_REQUEST);

        HttpClient httpClient = HttpClients.createDefault();
        String url = "http://localhost:8080/orders";
        HttpPost httpPost = new HttpPost(url);

        String JSON_STRING= new String(requestJson.readAllBytes(), StandardCharsets.UTF_8);
        StringEntity requestEntity = new StringEntity(
                JSON_STRING,
                ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);

        HttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();

        assertEquals(201, statusCode);
    }

    @Test
    public void testCreateOrderHttpPost_expectedInvalidRequest() throws IOException {
        InputStream requestJson = this.getClass().getResourceAsStream(TEST_CREATE_ORDER_INVALID_REQUEST);

        HttpClient httpClient = HttpClients.createDefault();
        String url = "http://localhost:8080/orders";
        HttpPost httpPost = new HttpPost(url);

        String JSON_STRING= new String(requestJson.readAllBytes(), StandardCharsets.UTF_8);
        StringEntity requestEntity = new StringEntity(
                JSON_STRING,
                ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);

        HttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();

        assertEquals(400, statusCode);
    }

}
