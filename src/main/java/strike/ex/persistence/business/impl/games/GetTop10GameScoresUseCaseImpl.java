package strike.ex.persistence.business.impl.games;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.GetTop10GamesScoresUseCase;
import strike.ex.persistence.domain.TopScoresRequest;
import strike.ex.persistence.repository.GameScoreRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GetTop10GameScoresUseCaseImpl implements GetTop10GamesScoresUseCase {
    private final GameScoreRepository gameScoreRepository;


    @Override
    public List<TopScoresRequest> getTop10GamesScores() {
        return gameScoreRepository.getTop10ByScore(PageRequest.ofSize(10));
    }
}
