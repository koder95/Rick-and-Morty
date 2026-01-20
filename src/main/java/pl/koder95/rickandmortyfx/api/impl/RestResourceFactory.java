package pl.koder95.rickandmortyfx.api.impl;

import lombok.RequiredArgsConstructor;
import pl.koder95.rickandmortyfx.api.data.Entity;
import pl.koder95.rickandmortyfx.api.Resource;
import pl.koder95.rickandmortyfx.api.ResourceFactory;
import tools.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;

@RequiredArgsConstructor
public class RestResourceFactory implements ResourceFactory {

    private final HttpClient client;
    private final ObjectMapper objectMapper;

    @Override
    public <T extends Entity> Resource<T> create(Class<T> clazz) {
        return new RestResource<>(client, clazz, objectMapper);
    }
}
