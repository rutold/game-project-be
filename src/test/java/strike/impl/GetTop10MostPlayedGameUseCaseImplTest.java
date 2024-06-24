package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.data.domain.PageRequest;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.games.GetTop10MostPlayedGameUseCaseImpl;
import strike.ex.persistence.domain.TopGamesRequest;
import strike.ex.persistence.repository.GameScoreRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class GetTop10MostPlayedGameUseCaseImplTest {

    @Mock
    private GameScoreRepository gameScoreRepository;

    @InjectMocks
    private GetTop10MostPlayedGameUseCaseImpl getTop10MostPlayedGameUseCase;

    @Test
    void testGetTop10MostPlayedGames() {
        List<TopGamesRequest> mockTopGames = List.of(new TopGamesRequest(), new TopGamesRequest());

        when(gameScoreRepository.findTop10MostPlayedGames(any(PageRequest.class))).thenReturn(mockTopGames);

        List<TopGamesRequest> result = getTop10MostPlayedGameUseCase.getTop10MostPlayedGames();

        assertEquals(2, result.size());
        verify(gameScoreRepository, times(1)).findTop10MostPlayedGames(any(PageRequest.class));
    }
}
