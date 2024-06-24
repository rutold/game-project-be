package strike.repository;

import strike.ex.persistence.entity.UserUpgradesEntity;

import java.util.List;
import java.util.Optional;

public interface IFakeUserUpgradesRepository {
    List<UserUpgradesEntity> findAll();
    Optional<UserUpgradesEntity> findById(long id);
    UserUpgradesEntity save(UserUpgradesEntity userUpgrade);
    void deleteById(long id);
}