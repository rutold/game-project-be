package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.games.AddGameScoreToGameUseCaseImpl;
import strike.ex.persistence.entity.GameEntity;
import strike.ex.persistence.entity.GameScoreEntity;
import strike.ex.persistence.entity.UserEntity;
import strike.ex.persistence.repository.GameRepository;
import strike.ex.persistence.repository.GameScoreRepository;
import strike.ex.persistence.repository.NewUserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class AddGameScoreToGameUseCaseImplTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameScoreRepository gameScoreRepository;

    @Mock
    private NewUserRepository userRepository;

    @InjectMocks
    private AddGameScoreToGameUseCaseImpl addGameScoreToGameUseCase;

    @Test
    void testAddGameScoreToGameSuccess() {
        String gameName = "Game1";
        Long userId = 1L;
        int score = 100;

        UserEntity user = new UserEntity();
        GameEntity game = new GameEntity();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(gameRepository.findByName(gameName)).thenReturn(Optional.of(game));

        addGameScoreToGameUseCase.AddGameScoreToGame(gameName, userId, score);

        verify(gameScoreRepository, times(1)).save(any(GameScoreEntity.class));
    }

    @Test
    void testAddGameScoreToGameUserOrGameNotFound() {
        String gameName = "Game1";
        Long userId = 1L;
        int score = 100;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            addGameScoreToGameUseCase.AddGameScoreToGame(gameName, userId, score);
        });

        assertEquals("User or Game not found", exception.getMessage());
        verify(gameScoreRepository, never()).save(any(GameScoreEntity.class));
    }
}
