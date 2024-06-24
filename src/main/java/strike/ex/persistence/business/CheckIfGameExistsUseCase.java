package strike.ex.persistence.business;


import strike.ex.persistence.entity.GameEntity;

import java.util.Optional;

public interface CheckIfGameExistsUseCase {
    Optional<GameEntity> CheckIfGameExists(Long userId);
}

