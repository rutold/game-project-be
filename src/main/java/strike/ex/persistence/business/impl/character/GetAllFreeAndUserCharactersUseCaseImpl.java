package strike.ex.persistence.business.impl.character;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.GetAllFreeAndUserCharacters;
import strike.ex.persistence.business.UserCharactersGetAllUseCase;
import strike.ex.persistence.domain.UserCharacterDTO;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.repository.CharacterRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class GetAllFreeAndUserCharactersUseCaseImpl implements GetAllFreeAndUserCharacters {

    private final CharacterRepository characterRepository;
    private final UserCharactersGetAllUseCase userCharactersGetAllUseCase;

    public List<CharacterEntity> getUserCharacters(long id) {
        List<UserCharacterDTO> charactersDTOs = userCharactersGetAllUseCase.getAllUserCharacters(id);
        return charactersDTOs.stream()
                .map(CharacterEntityConverter::mapToCharacterEntity)
                .collect(Collectors.toList());
    }
    @Override
    public List<CharacterEntity> getAllFreeAndUserCharacters(long id) {
        List<CharacterEntity> convertedCharacterEntities = getUserCharacters(id);
         List<CharacterEntity> retrievedCharacterEntities = characterRepository.findAllByCost();
        return Stream.concat(convertedCharacterEntities.stream(), retrievedCharacterEntities.stream()).collect(Collectors.toList());
    }
}
