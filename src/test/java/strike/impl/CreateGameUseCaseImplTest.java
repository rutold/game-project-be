package strike.impl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.games.CreateGameUseCaseImpl;
import strike.ex.persistence.domain.CreateGameRequest;
import strike.ex.persistence.entity.GameEntity;
import strike.ex.persistence.repository.GameRepository;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class CreateGameUseCaseImplTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private CreateGameUseCaseImpl createGameUseCase;

    @Test
    void testCreateGame() {
        CreateGameRequest request = new CreateGameRequest();
        request.setGame("Game1");
        request.setDifficulty("Hard");

        createGameUseCase.createGame(request);

        verify(gameRepository, times(1)).save(any(GameEntity.class));
    }
}
