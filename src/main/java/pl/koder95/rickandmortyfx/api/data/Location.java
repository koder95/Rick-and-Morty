package pl.koder95.rickandmortyfx.api.data;

import java.util.List;

public record Location(
        Long id,
        String name,
        String type,
        String dimension,
        List<String> residents,
        String url,
        String created
) implements Entity {
}
