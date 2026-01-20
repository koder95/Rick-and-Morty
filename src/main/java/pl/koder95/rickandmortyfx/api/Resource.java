package pl.koder95.rickandmortyfx.api;

import pl.koder95.rickandmortyfx.api.data.Entity;

public interface Resource<T extends Entity> {
    Page<T> getPage(Integer page);

    T getId(Long id);
}
