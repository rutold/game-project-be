package strike.ex.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.AbilityEntity;

@Repository
public interface AbilityRepository extends JpaRepository<AbilityEntity, Long> {

}
