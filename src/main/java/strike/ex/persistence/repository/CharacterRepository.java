package strike.ex.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.CharacterEntity;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    @Query("SELECT c FROM CharacterEntity c LEFT JOIN FETCH c.abilities")
    List<CharacterEntity> findAllWithAbilities();

    @Query("SELECT c FROM CharacterEntity c LEFT JOIN FETCH c.abilities WHERE c.name = :name")
    CharacterEntity findByNameWithAbilities(@Param("name") String name);

    @Query("SELECT c FROM CharacterEntity c LEFT JOIN FETCH c.abilities WHERE c.cost = 0 ")
    List<CharacterEntity> findAllByCost();
}
