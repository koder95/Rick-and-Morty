package pl.koder95.rickandmortyfx.service.impl;

import lombok.RequiredArgsConstructor;
import pl.koder95.rickandmortyfx.api.Resources;
import pl.koder95.rickandmortyfx.api.data.Character;
import pl.koder95.rickandmortyfx.dto.CharacterViewDto;
import pl.koder95.rickandmortyfx.mapper.ImportMapper;
import pl.koder95.rickandmortyfx.service.ImportService;

import java.util.Optional;

@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService {

    private final Resources resources;
    private final ImportMapper importMapper;

    @Override
    public Optional<CharacterViewDto> importCharacter(Long id) {
        Character found = resources.characters().getId(id);
        return Optional.ofNullable(found).map(importMapper::toViewDto);
    }
}
