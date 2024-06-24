package strike.ex.persistence.business.impl.character;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.CharacterGetAllUseCase;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.repository.CharacterRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CharacterGetAllUseCaseImpl implements CharacterGetAllUseCase {

    private final CharacterRepository characterRepository;

    @Override
    public List<CharacterEntity> getAllCharactersWithAbilities() {
        return characterRepository.findAllWithAbilities();
    }
}



