package strike.repository;

import strike.ex.persistence.entity.EnemyEntity;

import java.util.List;
import java.util.Optional;

public interface IFakeEnemyRepository {
    List<EnemyEntity> findAll();
    Optional<EnemyEntity> findById(long id);
    EnemyEntity save(EnemyEntity enemy);
    void deleteById(long id);
}
