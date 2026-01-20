package pl.koder95.rickandmortyfx.api.data;

import java.util.List;

public record Episode(
        Long id,
        String name,
        String airDate,
        String episode,
        List<String> characters,
        String url,
        String created
) implements Entity {
}
