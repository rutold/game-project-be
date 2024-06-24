package strike.ex.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.GameEntity;

import java.util.Optional;


@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    Optional<GameEntity> findByName(String name);

}

