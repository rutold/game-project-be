package strike.ex.persistence.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.GameUiGetAllUseCase;
import strike.ex.persistence.entity.GameUiEntity;
import strike.ex.persistence.repository.GameUiRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GameUiGetAllUseCaseImpl implements GameUiGetAllUseCase {

    private final GameUiRepository gameDataRepository;

    @Override
    public List<GameUiEntity> getAllGameUi() {
        return gameDataRepository.findAll();
    }
}



