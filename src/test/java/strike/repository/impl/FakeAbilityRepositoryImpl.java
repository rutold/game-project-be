package strike.repository.impl;

import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.AbilityEntity;
import strike.repository.IFakeAbilityRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeAbilityRepositoryImpl implements IFakeAbilityRepository {
    private static long NEXT_ID = 1;
    private final List<AbilityEntity> abilities = new ArrayList<>();

    @Override
    public List<AbilityEntity> findAll() {
        return Collections.unmodifiableList(abilities);
    }

    @Override
    public Optional<AbilityEntity> findById(long id) {
        return abilities.stream().filter(ability -> ability.getId().equals(id)).findFirst();
    }

    @Override
    public AbilityEntity save(AbilityEntity ability) {
        if (ability.getId() == null) {
            ability.setId(NEXT_ID++);
        }
        abilities.add(ability);
        return ability;
    }

    @Override
    public void deleteById(long id) {
        abilities.removeIf(ability -> ability.getId().equals(id));
    }
}
