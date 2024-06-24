package strike.ex.persistence.business;
import strike.ex.persistence.domain.UserCharacterDTO;

import java.util.List;

public interface UserCharactersGetAllUseCase {
    List<UserCharacterDTO> getAllUserCharacters(long id);
}