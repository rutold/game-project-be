package strike.ex.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.ExpEntity;

@Repository
public interface ExpRepository extends JpaRepository<ExpEntity, Long> {

}
