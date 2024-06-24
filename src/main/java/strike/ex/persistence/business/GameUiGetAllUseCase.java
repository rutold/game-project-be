package strike.ex.persistence.business;

import strike.ex.persistence.entity.GameUiEntity;

import java.util.List;

public interface GameUiGetAllUseCase {
    List<GameUiEntity> getAllGameUi();
}
