package strike.ex.persistence.business;
import strike.ex.persistence.entity.UserCharactersEntity;
import java.util.Optional;

public interface GetUserCharacterUseCase {
    Optional<UserCharactersEntity> getUserCharacter(long characterId, long userId);
}