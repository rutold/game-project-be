package strike.ex.persistence.business;


public interface AddGameScoreToGameUseCase {
    void AddGameScoreToGame(String game, Long userId,int score);

}

