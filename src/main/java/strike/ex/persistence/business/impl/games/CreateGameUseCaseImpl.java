package strike.ex.persistence.business.impl.games;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.CreateGameUseCase;
import strike.ex.persistence.domain.*;
import strike.ex.persistence.entity.GameEntity;
import strike.ex.persistence.repository.GameRepository;

@Service
@AllArgsConstructor
public class CreateGameUseCaseImpl implements CreateGameUseCase {   
    private final GameRepository gameRepository;


    @Override
    public void createGame(CreateGameRequest createGameRequest) {
        saveNewGame(createGameRequest);
    }

    private void saveNewGame(CreateGameRequest createGameRequest) {

        GameEntity newGame = GameEntity.builder()
                .name(createGameRequest.getGame())
                .difficulty(createGameRequest.getDifficulty())
                .build();


        gameRepository.save(newGame);
    }
}

