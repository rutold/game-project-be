package strike.ex.persistence.business.impl.character;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.GetCharacterUseCase;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.repository.CharacterRepository;



@Service
@AllArgsConstructor
public class GetCharacterUseCaseImpl implements GetCharacterUseCase {

    private final CharacterRepository characterRepository;

    @Override
    public CharacterEntity getCharactersWithAbilities(String name) {
        return characterRepository.findByNameWithAbilities(name);
    }
}



