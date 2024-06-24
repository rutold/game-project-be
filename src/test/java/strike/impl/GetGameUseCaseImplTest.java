package strike.impl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.games.GetGameUseCaseImpl;
import strike.ex.persistence.entity.GameEntity;
import strike.ex.persistence.repository.GameRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class GetGameUseCaseImplTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GetGameUseCaseImpl getGameUseCase;

    @Test
    void testGetGame() {
        String gameName = "Game1";
        GameEntity game = new GameEntity();

        when(gameRepository.findByName(gameName)).thenReturn(Optional.of(game));

        Optional<GameEntity> result = getGameUseCase.getGame(gameName);

        assertTrue(result.isPresent());
        assertEquals(game, result.get());
    }
}
