package strike.repository;

import strike.ex.persistence.entity.GameUiEntity;

import java.util.List;
import java.util.Optional;

public interface IFakeGameUiRepository {
    List<GameUiEntity> findAll();
    Optional<GameUiEntity> findById(long id);
    GameUiEntity save(GameUiEntity gameUi);
    void deleteById(long id);
}
