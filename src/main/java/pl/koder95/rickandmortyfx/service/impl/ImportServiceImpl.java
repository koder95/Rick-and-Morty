package pl.koder95.rickandmortyfx.service.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.koder95.rickandmortyfx.api.Resources;
import pl.koder95.rickandmortyfx.api.data.Character;
import pl.koder95.rickandmortyfx.dto.CharacterViewDto;
import pl.koder95.rickandmortyfx.mapper.ImportMapper;
import pl.koder95.rickandmortyfx.service.ImportService;

@Service
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
