package pl.koder95.rickandmortyfx.mapper.impl;

import java.time.LocalDateTime;
import java.util.List;
import javafx.scene.image.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.koder95.rickandmortyfx.api.Endpoint;
import pl.koder95.rickandmortyfx.api.Resources;
import pl.koder95.rickandmortyfx.api.data.Character;
import pl.koder95.rickandmortyfx.api.data.Episode;
import pl.koder95.rickandmortyfx.api.data.Location;
import pl.koder95.rickandmortyfx.api.data.NamedUrl;
import pl.koder95.rickandmortyfx.dto.CharacterViewDto;
import pl.koder95.rickandmortyfx.dto.EpisodeLinkDto;
import pl.koder95.rickandmortyfx.dto.LocationLinkDto;
import pl.koder95.rickandmortyfx.mapper.ImportMapper;

@Component
@RequiredArgsConstructor
public class ImportMapperImpl implements ImportMapper {

    private final Resources resources;

    @Override
    public Character toApiEntity(CharacterViewDto characterViewDto) {
        return new Character(
                characterViewDto.id().orElse(null),
                characterViewDto.name().orElse(null),
                characterViewDto.status().orElse(null),
                characterViewDto.species().orElse(null),
                characterViewDto.type().orElse(null),
                characterViewDto.gender().orElse(null),
                characterViewDto.origin().map(this::fromLocation).orElse(null),
                characterViewDto.location().map(this::fromLocation).orElse(null),
                characterViewDto.avatar().map(Image::getUrl).orElse(null),
                toApiEntityList(characterViewDto.episodes().orElse(List.of())),
                characterViewDto.created().map(LocalDateTime::parse).orElse(null)
        );
    }

    private List<String> toApiEntityList(List<EpisodeLinkDto> episodes) {
        return episodes.stream()
                .map(episodeLinkDto -> Endpoint.EPISODE.getIdUrl(episodeLinkDto.id()))
                .toList();
    }

    private NamedUrl fromLocation(LocationLinkDto dto) {
        return new NamedUrl(dto.name(), Endpoint.LOCATION.getIdUrl(dto.id()));
    }

    @Override
    public CharacterViewDto toViewDto(Character character) {
        return new CharacterViewDto(
                character.id(),
                character.name(),
                character.status(),
                character.species(),
                character.type(),
                character.gender(),
                toViewDto(character.origin()),
                toViewDto(character.location()),
                new Image(character.image()),
                character.episode()
                        .stream()
                        .map(this::toViewDto)
                        .toList(),
                String.valueOf(character.created())
        );
    }

    private LocationLinkDto toViewDto(NamedUrl location) {
        if (location.url() == null || location.url().isBlank()) {
            return null;
        }
        Long id = getIdFromUrl(location.url());
        Location data = resources.locations().getId(id);
        return new LocationLinkDto(id, location.name(), data.type(), data.dimension());
    }

    private EpisodeLinkDto toViewDto(String url) {
        if (url == null || url.isBlank()) {
            return null;
        }
        Long id = getIdFromUrl(url);
        Episode episode = resources.episodes().getId(id);
        return new EpisodeLinkDto(id, episode.episode(), episode.name());
    }

    private static long getIdFromUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("url is null or blank");
        }
        String idSuffix = url.substring(url.lastIndexOf('/') + 1);
        return Long.parseLong(idSuffix);
    }
}
