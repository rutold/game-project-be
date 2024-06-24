package strike.repository.impl;

import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.EnemyEntity;
import strike.repository.IFakeEnemyRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeEnemyRepositoryImpl implements IFakeEnemyRepository {
    private static long NEXT_ID = 1;
    private final List<EnemyEntity> enemies = new ArrayList<>();

    @Override
    public List<EnemyEntity> findAll() {
        return Collections.unmodifiableList(enemies);
    }

    @Override
    public Optional<EnemyEntity> findById(long id) {
        return enemies.stream().filter(enemy -> enemy.getId().equals(id)).findFirst();
    }

    @Override
    public EnemyEntity save(EnemyEntity enemy) {
        if (enemy.getId() == null) {
            enemy.setId(NEXT_ID++);
        }
        enemies.add(enemy);
        return enemy;
    }

    @Override
    public void deleteById(long id) {
        enemies.removeIf(enemy -> enemy.getId().equals(id));
    }
}
