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
                               Optional<List<LocationLinkDto>> locations,
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
                            List<LocationLinkDto> locations,
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
                Optional.ofNullable(locations),
                Optional.ofNullable(avatar),
                Optional.ofNullable(episodes),
                Optional.ofNullable(created)
        );
    }
}
