package pl.koder95.rickandmortyfx.api.impl;

import lombok.RequiredArgsConstructor;
import pl.koder95.rickandmortyfx.api.*;
import pl.koder95.rickandmortyfx.api.data.Entity;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
class RestResource<T extends Entity> implements Resource<T> {

    private static final int SLEEP_MILLIS_AFTER_RESPONSE = 50; // 1000 ms / 50 ms = 200 (requests per sec)
    private final HttpClient client;
    private final Class<T> entityType;
    private final ObjectMapper objectMapper;

    private Endpoint getEndpoint() {
        return Endpoint.type(entityType);
    }

    private T getEntity(HttpResponse<String> httpResponse) {
        return objectMapper.readValue(httpResponse.body(), entityType);
    }

    @SuppressWarnings("unchecked")
    private Page<T> getPage(HttpResponse<String> httpResponse) {
        return objectMapper.readValue(httpResponse.body(), Page.class);
    }

    private HttpResponse<String> sendRequest(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();
        try {
            HttpResponse<String> response = client.send(request, bodyHandler);
            Thread.sleep(SLEEP_MILLIS_AFTER_RESPONSE);
            return response;
        } catch (IOException e) {
            throw new InvalidConnectionException("Cannot send request or receive response", e);
        } catch (InterruptedException e) {
            throw new InvalidConnectionException(
                    "Sending request or receiving response was interrupted", e
            );
        }
    }

    @Override
    public Page<T> getPage(Integer page) {
        return getPage(sendRequest(getEndpoint().getPageUrl(page)));
    }

    @Override
    public T getId(Long id) {
        return getEntity(sendRequest(getEndpoint().getIdUrl(id)));
    }
}
