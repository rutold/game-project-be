package strike.repository.impl;

import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.UpgradesEntity;
import strike.repository.IFakeUpgradesRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeUpgradesRepositoryImpl implements IFakeUpgradesRepository {
    private static long NEXT_ID = 1;
    private final List<UpgradesEntity> upgrades = new ArrayList<>();

    @Override
    public List<UpgradesEntity> findAll() {
        return Collections.unmodifiableList(upgrades);
    }

    @Override
    public Optional<UpgradesEntity> findById(long id) {
        return upgrades.stream().filter(upgrade -> upgrade.getId().equals(id)).findFirst();
    }

    @Override
    public UpgradesEntity save(UpgradesEntity upgrade) {
        if (upgrade.getId() == null) {
            upgrade.setId(NEXT_ID++);
        }
        upgrades.add(upgrade);
        return upgrade;
    }

    @Override
    public void deleteById(long id) {
        upgrades.removeIf(upgrade -> upgrade.getId().equals(id));
    }
}