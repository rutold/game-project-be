package strike.ex.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.UserUpgradesEntity;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserUpgradesRepository extends JpaRepository<UserUpgradesEntity, Long> {
    Optional<UserUpgradesEntity> findByUser_IdAndUpgrade_Id(Long userId, Long upgradeId);
    List<UserUpgradesEntity> findAllByUser_Id(Long userId);
}

