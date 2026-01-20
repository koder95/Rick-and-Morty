package pl.koder95.rickandmortyfx.mapper;

import pl.koder95.rickandmortyfx.api.data.Character;
import pl.koder95.rickandmortyfx.dto.CharacterViewDto;

public interface ImportMapper {

    Character toApiEntity(CharacterViewDto characterViewDto);
    CharacterViewDto toViewDto(Character character);
}
