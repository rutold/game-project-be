package strike.ex.persistence.business;


import strike.ex.persistence.entity.EnemyEntity;

import java.util.List;

public interface EnemyGetAllUseCase {
    List<EnemyEntity> getAllEnemies();
}
