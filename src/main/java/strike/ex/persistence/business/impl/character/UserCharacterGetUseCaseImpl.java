package strike.ex.persistence.business.impl.character;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.GetUserCharacterUseCase;
import strike.ex.persistence.entity.UserCharactersEntity;
import strike.ex.persistence.repository.UserCharactersRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserCharacterGetUseCaseImpl implements GetUserCharacterUseCase {
    private final UserCharactersRepository userCharactersRepository;

    @Override
    public Optional<UserCharactersEntity> getUserCharacter(long userId, long characterId) {
        return userCharactersRepository.findByUser_IdAndCharacter_Id(userId, characterId);
    }
}



