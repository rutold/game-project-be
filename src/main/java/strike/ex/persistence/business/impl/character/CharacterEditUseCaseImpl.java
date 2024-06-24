package strike.ex.persistence.business.impl.character;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.CharacterEditUseCase;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.repository.CharacterRepository;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CharacterEditUseCaseImpl implements CharacterEditUseCase {

    private final CharacterRepository characterRepository;
    @Override
    public void EditCharacter(CharacterEntity entity) {
        Optional<CharacterEntity> characterEntity = characterRepository.findById(entity.getId());
        if (characterEntity.isPresent()){
        characterRepository.save(entity);
        }
    }
}



