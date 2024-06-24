package strike.ex.persistence.business;
import strike.ex.persistence.domain.CreateGameRequest;

public interface CreateGameUseCase {
    void createGame(CreateGameRequest createGameRequest);
}
