package strike.ex.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.EnemyEntity;

@Repository
public interface EnemyRepository extends JpaRepository<EnemyEntity, Long> {

}
