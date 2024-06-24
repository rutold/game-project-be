package strike.ex.persistence.business;


import strike.ex.persistence.domain.TopGamesRequest;
import java.util.List;

public interface GetTop10MostPlayedGamesUseCase {

    List<TopGamesRequest> getTop10MostPlayedGames();
}

