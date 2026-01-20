package pl.koder95.rickandmortyfx.service;

import pl.koder95.rickandmortyfx.dto.CharacterViewDto;

import java.util.Optional;

public interface ImportService {
    Optional<CharacterViewDto> importCharacter(Long id);
}
