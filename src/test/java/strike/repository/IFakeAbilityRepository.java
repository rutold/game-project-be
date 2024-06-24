package strike.repository;

import strike.ex.persistence.entity.AbilityEntity;

import java.util.List;
import java.util.Optional;

public interface IFakeAbilityRepository {
    List<AbilityEntity> findAll();
    Optional<AbilityEntity> findById(long id);
    AbilityEntity save(AbilityEntity ability);
    void deleteById(long id);
}
