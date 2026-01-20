package pl.koder95.rickandmortyfx.api;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import pl.koder95.rickandmortyfx.api.data.Character;
import pl.koder95.rickandmortyfx.api.data.Episode;
import pl.koder95.rickandmortyfx.api.data.Location;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Resources {
    private final Resource<Character> characters;
    private final Resource<Episode> episodes;
    private final Resource<Location> locations;

    public Resources(ResourceFactory resourceFactory) {
        this(
                resourceFactory.create(Character.class),
                resourceFactory.create(Episode.class),
                resourceFactory.create(Location.class)
        );
    }

    public Resource<Character> characters() {
        return this.characters;
    }

    public Resource<Episode> episodes() {
        return this.episodes;
    }

    public Resource<Location> locations() {
        return this.locations;
    }
}
