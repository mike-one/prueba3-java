package com.cuenca.http;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CuencaClientTest {
    HttpClient httpClient = mock(HttpClient.class) ;
    String API_KEY="someAPI_KEY";
    String API_SECRET="someAPI_SECRET";

    private String getAuthHeader() {
        String plainCredentials = API_KEY + ":" + API_SECRET;
        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
        String authorizationHeader = "Basic " + base64Credentials;
        return authorizationHeader;
    }

    HttpResponse<String> response = new HttpResponse<String>() {
        @Override
        public int statusCode() {
            return 200;
        }

        @Override
        public HttpRequest request() {
            return null;
        }

        @Override
        public Optional<HttpResponse<String>> previousResponse() {
            return Optional.empty();
        }

        @Override
        public HttpHeaders headers() {
            return null;
        }

        @Override
        public String body() {
            return "{\"id\":\"STkUpzYcbFFFFFGlCnINfa\",\"created_at\":\"2021-03-04T18:56:53.189000\"," +
                    "\"user_id\":\"US46cuFFFFFFTOceMKVqSzF\",\"amount\":55555,\"status\":\"submitted\"," +
                    "\"descriptor\":\"Pago de servicio\",\"account_number\":\"(556) 093-4315\"," +
                    "\"provider_uri\":\"/service_providers/SP5MQxAQbFFFFFW4Rpgqt8AK\"}";
        }

        @Override
        public Optional<SSLSession> sslSession() {
            return Optional.empty();
        }

        @Override
        public URI uri() {
            return null;
        }

        @Override
        public HttpClient.Version version() {
            return null;
        }
    };

    @Test
    public void getClient() throws Exception {
        String endPoint = "https://stage.cuenca.com/get";
        HttpRequest.BodyPublisher jsonPayload = HttpRequest.BodyPublishers.ofString("");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .method("GET", jsonPayload)
                .setHeader("Authorization", getAuthHeader())
                .header("Content-Type", "application/json")
                .build();
        when(httpClient.send(request,HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        CuencaClient client = new CuencaClient(API_KEY,API_SECRET, httpClient);
        var response = client.get(endPoint);
        Assertions.assertEquals(response.statusCode(),200);
    }

    @Test
    public void postClient() throws Exception {
        String endPoint = "https://stage.cuenca.com/post";
        String boody = "foo";
        HttpRequest.BodyPublisher jsonPayload = HttpRequest.BodyPublishers.ofString(boody);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .method("POST", jsonPayload)
                .setHeader("Authorization", getAuthHeader())
                .header("Content-Type", "application/json")
                .build();
        when(httpClient.send(request,HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        CuencaClient client = new CuencaClient(API_KEY,API_SECRET, httpClient);
        var response = client.post(endPoint,boody);
        Assertions.assertEquals(response.statusCode(),200);
    }

    @Test
    public void patchClient() throws Exception {
        String endPoint = "https://stage.cuenca.com/patch";
        String boody = "foo";
        HttpRequest.BodyPublisher jsonPayload = HttpRequest.BodyPublishers.ofString(boody);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .method("PATCH", jsonPayload)
                .setHeader("Authorization", getAuthHeader())
                .header("Content-Type", "application/json")
                .build();
        when(httpClient.send(request,HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        CuencaClient client = new CuencaClient(API_KEY,API_SECRET, httpClient);
        var response = client.patch(endPoint,boody);
        Assertions.assertEquals(response.statusCode(),200);
    }

    @Test
    public void deleteClient() throws Exception {
        String endPoint = "https://stage.cuenca.com/delete";
        String boody = "";
        HttpRequest.BodyPublisher jsonPayload = HttpRequest.BodyPublishers.ofString(boody);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endPoint))
                .method("DELETE", jsonPayload)
                .setHeader("Authorization", getAuthHeader())
                .header("Content-Type", "application/json")
                .build();
        when(httpClient.send(request,HttpResponse.BodyHandlers.ofString())).thenReturn(response);
        CuencaClient client = new CuencaClient(API_KEY,API_SECRET, httpClient);
        var response = client.delete(endPoint);
        Assertions.assertEquals(response.statusCode(),200);
    }
}