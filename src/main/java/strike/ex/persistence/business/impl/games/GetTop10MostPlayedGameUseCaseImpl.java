package strike.ex.persistence.business.impl.games;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.GetTop10MostPlayedGamesUseCase;
import strike.ex.persistence.domain.TopGamesRequest;
import strike.ex.persistence.repository.GameScoreRepository;
import java.util.List;

@Service
@AllArgsConstructor
public class GetTop10MostPlayedGameUseCaseImpl implements GetTop10MostPlayedGamesUseCase {
    private final GameScoreRepository gameScoreRepository;

    @Override
    public List<TopGamesRequest> getTop10MostPlayedGames() {


        return gameScoreRepository.findTop10MostPlayedGames(PageRequest.ofSize( 10));
    }}
