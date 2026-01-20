package pl.koder95.rickandmortyfx.api;

import pl.koder95.rickandmortyfx.api.data.Entity;

public interface ResourceFactory {
    <T extends Entity> Resource<T> create(Class<T> clazz);
}
