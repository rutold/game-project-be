package strike.ex.persistence.business.impl.games;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.GetGamesUseCase;
import strike.ex.persistence.entity.GameEntity;
import strike.ex.persistence.repository.GameRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetGameUseCaseImpl implements GetGamesUseCase {
    private final GameRepository gameRepository;

    @Override
    public Optional<GameEntity> getGame(String game) {
        return gameRepository.findByName(game);
    }
}
