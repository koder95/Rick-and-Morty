package pl.koder95.rickandmortyfx.api.data;

import java.time.LocalDateTime;
import java.util.List;

public record Character(
        Long id,
        String name,
        String status,
        String species,
        String type,
        String gender,
        NamedUrl origin,
        NamedUrl location,
        String image,
        List<String> episode,
        LocalDateTime created
) implements Entity {
}
