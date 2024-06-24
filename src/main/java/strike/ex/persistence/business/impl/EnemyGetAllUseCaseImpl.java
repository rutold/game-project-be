package strike.ex.persistence.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.EnemyGetAllUseCase;
import strike.ex.persistence.entity.EnemyEntity;
import strike.ex.persistence.repository.EnemyRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EnemyGetAllUseCaseImpl implements EnemyGetAllUseCase {

    private final EnemyRepository gameDataRepository;

    @Override
    public List<EnemyEntity> getAllEnemies() {
        return gameDataRepository.findAll();
    }
}



