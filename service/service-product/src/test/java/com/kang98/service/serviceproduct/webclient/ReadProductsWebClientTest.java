package com.kang98.service.serviceproduct.webclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadProductsWebClientTest {

    @Test
    public void testHttpGet() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        String url = "http://localhost:8080/products/all";
        HttpPost httpPost = new HttpPost(url);
        HttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
//        String responseBody = EntityUtils.toString(response.getEntity());

        assertEquals(200, statusCode);
//        assertEquals("{\"userId\":1,\"id\":1,\"title\":\"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\"body\":\"quia et suscipit\\nsuscipit...\",\"", responseBody.substring(0, 100));
    }
}
