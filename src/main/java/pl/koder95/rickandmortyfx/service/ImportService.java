package pl.koder95.rickandmortyfx.service;

import java.util.Optional;
import pl.koder95.rickandmortyfx.dto.CharacterViewDto;

public interface ImportService {
    Optional<CharacterViewDto> importCharacter(Long id);

    long getCharactersCount();
}
