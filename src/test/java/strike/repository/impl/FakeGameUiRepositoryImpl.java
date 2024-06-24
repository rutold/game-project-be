package strike.repository.impl;

import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.GameUiEntity;
import strike.repository.IFakeGameUiRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeGameUiRepositoryImpl implements IFakeGameUiRepository {
    private static long NEXT_ID = 1;
    private final List<GameUiEntity> gameUis = new ArrayList<>();

    @Override
    public List<GameUiEntity> findAll() {
        return Collections.unmodifiableList(gameUis);
    }

    @Override
    public Optional<GameUiEntity> findById(long id) {
        return gameUis.stream().filter(gameUi -> gameUi.getId().equals(id)).findFirst();
    }

    @Override
    public GameUiEntity save(GameUiEntity gameUi) {
        if (gameUi.getId() == null) {
            gameUi.setId(NEXT_ID++);
        }
        gameUis.add(gameUi);
        return gameUi;
    }

    @Override
    public void deleteById(long id) {
        gameUis.removeIf(gameUi -> gameUi.getId().equals(id));
    }
}
