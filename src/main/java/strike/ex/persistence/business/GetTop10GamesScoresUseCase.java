package strike.ex.persistence.business;


import strike.ex.persistence.domain.TopScoresRequest;
import java.util.List;

public interface GetTop10GamesScoresUseCase {

    List<TopScoresRequest> getTop10GamesScores();
}

