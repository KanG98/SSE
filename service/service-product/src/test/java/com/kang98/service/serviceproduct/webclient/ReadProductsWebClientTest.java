package com.kang98.service.serviceproduct.webclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadProductsWebClientTest {

    @Test
    public void testProductAllHttpPost_expectedSuccess() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        String url = "http://localhost:8080/products/all";
        HttpPost httpPost = new HttpPost(url);
        HttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();

        assertEquals(200, statusCode);
    }

    @Test
    public void testProductByNameHttpPost_expectedSuccess() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        String url = "http://localhost:8080/products/by-name";
        HttpPost httpPost = new HttpPost(url);

        String JSON_STRING = "{ \"productName\": \"smartphone\" }";
        StringEntity requestEntity = new StringEntity(
                JSON_STRING,
                ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);

        HttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();

        assertEquals(200, statusCode);
    }

    @Test
    public void testProductByNameHttpPost_givenNoRequestBody_expectedInvalidRequest() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        String url = "http://localhost:8080/products/by-name";
        HttpPost httpPost = new HttpPost(url);

        HttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();

        assertEquals(400, statusCode);
    }

    @Test
    public void testProductByNameHttpPost_givenNoProductName_expectedInvalidRequest() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        String url = "http://localhost:8080/products/by-name";
        HttpPost httpPost = new HttpPost(url);

        String JSON_STRING = "{}";
        StringEntity requestEntity = new StringEntity(
                JSON_STRING,
                ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);

        HttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();

        assertEquals(400, statusCode);
    }
}
