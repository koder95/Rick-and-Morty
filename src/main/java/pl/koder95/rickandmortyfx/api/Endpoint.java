package pl.koder95.rickandmortyfx.api;

import lombok.Getter;
import pl.koder95.rickandmortyfx.api.data.Character;
import pl.koder95.rickandmortyfx.api.data.Entity;
import pl.koder95.rickandmortyfx.api.data.Episode;
import pl.koder95.rickandmortyfx.api.data.Location;

import java.util.Arrays;
import java.util.Objects;

public enum Endpoint {
    CHARACTER("https://rickandmortyapi.com/api/character", Character.class),
    LOCATION("https://rickandmortyapi.com/api/location", Location.class),
    EPISODE("https://rickandmortyapi.com/api/episode", Episode.class);

    private final UrlSchema urlSchema;
    @Getter
    private final Class<? extends Entity> entityType;

    Endpoint(String urlSchema, Class<? extends Entity> entityType) {
        this.urlSchema = new UrlSchema(urlSchema);
        this.entityType = entityType;
    }

    public String getPageUrl(Integer page) {
        return urlSchema.pageUrl(page);
    }

    public String getIdUrl(Long id) {
        return urlSchema.idUrl(id);
    }

    public static Endpoint type(Class<? extends Entity> entityType) {
        return Arrays.stream(Endpoint.values())
                .filter(endpoint -> Objects.equals(endpoint.getEntityType(), entityType))
                .findFirst()
                .orElse(null);
    }
}
