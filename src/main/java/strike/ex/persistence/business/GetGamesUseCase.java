package strike.ex.persistence.business;


import strike.ex.persistence.entity.GameEntity;

import java.util.Optional;

public interface GetGamesUseCase {

    Optional<GameEntity> getGame(String game);
}

