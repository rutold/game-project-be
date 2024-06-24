package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.data.domain.PageRequest;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.games.GetTop10GameScoresUseCaseImpl;
import strike.ex.persistence.domain.TopScoresRequest;
import strike.ex.persistence.repository.GameScoreRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class GetTop10GameScoresUseCaseImplTest {

    @Mock
    private GameScoreRepository gameScoreRepository;

    @InjectMocks
    private GetTop10GameScoresUseCaseImpl getTop10GameScoresUseCase;

    @Test
    void testGetTop10GamesScores() {
        List<TopScoresRequest> mockTopScores = List.of(new TopScoresRequest(), new TopScoresRequest());

        when(gameScoreRepository.getTop10ByScore(any(PageRequest.class))).thenReturn(mockTopScores);

        List<TopScoresRequest> result = getTop10GameScoresUseCase.getTop10GamesScores();

        assertEquals(2, result.size());
        verify(gameScoreRepository, times(1)).getTop10ByScore(any(PageRequest.class));
    }
}
