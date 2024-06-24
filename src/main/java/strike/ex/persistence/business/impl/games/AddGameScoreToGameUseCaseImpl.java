package strike.ex.persistence.business.impl.games;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.AddGameScoreToGameUseCase;
import strike.ex.persistence.entity.*;
import strike.ex.persistence.repository.GameRepository;
import strike.ex.persistence.repository.GameScoreRepository;
import strike.ex.persistence.repository.NewUserRepository;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddGameScoreToGameUseCaseImpl implements AddGameScoreToGameUseCase {
    private final GameRepository gameRepository;
    private final GameScoreRepository gameScoreRepository;
    private  final NewUserRepository userRepository;

    @Override
    public void AddGameScoreToGame(String game, Long userId, int score) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        Optional<GameEntity> gameOpt = gameRepository.findByName(game);
        if (userOpt.isEmpty() || gameOpt.isEmpty()) {
            throw new IllegalArgumentException("User or Game not found");
        }
        UserEntity userEntity = userOpt.get();
        GameEntity gameEntity = gameOpt.get();
            GameScoreEntity newScore = GameScoreEntity.builder()
                    .gameEntity(gameEntity)
                    .userEntity(userEntity)
                    .score(score)
                    .build();
            gameScoreRepository.save(newScore);
    }
}
