package strike.repository;

import strike.ex.persistence.entity.UpgradesEntity;

import java.util.List;
import java.util.Optional;

public interface IFakeUpgradesRepository {
    List<UpgradesEntity> findAll();
    Optional<UpgradesEntity> findById(long id);
    UpgradesEntity save(UpgradesEntity upgrade);
    void deleteById(long id);
}