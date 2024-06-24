package strike.ex.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.GameUiEntity;

@Repository
public interface GameUiRepository extends JpaRepository<GameUiEntity, Long> {

}
