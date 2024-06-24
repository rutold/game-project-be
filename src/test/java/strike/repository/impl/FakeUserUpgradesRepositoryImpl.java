package strike.repository.impl;

import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.UserUpgradesEntity;
import strike.repository.IFakeUserUpgradesRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeUserUpgradesRepositoryImpl implements IFakeUserUpgradesRepository {
    private static long NEXT_ID = 1;
    private final List<UserUpgradesEntity> userUpgrades = new ArrayList<>();

    @Override
    public List<UserUpgradesEntity> findAll() {
        return Collections.unmodifiableList(userUpgrades);
    }

    @Override
    public Optional<UserUpgradesEntity> findById(long id) {
        return userUpgrades.stream().filter(userUpgrade -> userUpgrade.getId().equals(id)).findFirst();
    }

    @Override
    public UserUpgradesEntity save(UserUpgradesEntity userUpgrade) {
        if (userUpgrade.getId() == null) {
            userUpgrade.setId(NEXT_ID++);
        }
        userUpgrades.add(userUpgrade);
        return userUpgrade;
    }

    @Override
    public void deleteById(long id) {
        userUpgrades.removeIf(userUpgrade -> userUpgrade.getId().equals(id));
    }
}