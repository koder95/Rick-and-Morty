package pl.koder95.rickandmortyfx.dto;

import javafx.scene.image.Image;

import java.util.List;
import java.util.Optional;

public record CharacterViewDto(Optional<Long> id,
                               Optional<String> name,
                               Optional<String> status,
                               Optional<String> species,
                               Optional<String> type,
                               Optional<String> gender,
                               Optional<LocationLinkDto> origin,
                               Optional<LocationLinkDto> location,
                               Optional<Image> avatar,
                               Optional<List<EpisodeLinkDto>> episodes,
                               Optional<String> created) {
    public CharacterViewDto {}

    public CharacterViewDto(Long id,
                            String name,
                            String status,
                            String species,
                            String type,
                            String gender,
                            LocationLinkDto origin,
                            LocationLinkDto location,
                            Image avatar,
                            List<EpisodeLinkDto> episodes,
                            String created) {
        this(
                Optional.ofNullable(id),
                Optional.ofNullable(name),
                Optional.ofNullable(status),
                Optional.ofNullable(species),
                Optional.ofNullable(type),
                Optional.ofNullable(gender),
                Optional.ofNullable(origin),
                Optional.ofNullable(location),
                Optional.ofNullable(avatar),
                Optional.ofNullable(episodes),
                Optional.ofNullable(created)
        );
    }
}
