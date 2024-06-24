package strike.ex.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.UpgradesEntity;


@Repository
public interface UpgradesRepository extends JpaRepository<UpgradesEntity, Long> {
}
