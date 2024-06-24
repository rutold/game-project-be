package strike.ex.persistence.business.impl.character;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.FreeCharacterGetAllUseCase;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.repository.CharacterRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FreeCharacterGetAllUseCaseImpl implements FreeCharacterGetAllUseCase {

    private final CharacterRepository characterRepository;

    @Override
    public List<CharacterEntity> getAllFreeCharacters() {
        return characterRepository.findAllByCost();
    }
}



