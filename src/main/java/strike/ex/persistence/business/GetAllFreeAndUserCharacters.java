package strike.ex.persistence.business;


import strike.ex.persistence.entity.CharacterEntity;

import java.util.List;

public interface GetAllFreeAndUserCharacters {
     List<CharacterEntity> getAllFreeAndUserCharacters(long userId);
}

