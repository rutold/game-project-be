package strike.ex.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.UserCharactersEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCharactersRepository extends JpaRepository<UserCharactersEntity, Long> {
    Optional<UserCharactersEntity> findByUser_IdAndCharacter_Id(Long userId, Long characterId);
    @Query("SELECT DISTINCT uc FROM UserCharactersEntity uc " +
            "JOIN FETCH uc.character c " +
            "LEFT JOIN FETCH c.abilities a " +
            "WHERE uc.user.id = :userId")
    List<UserCharactersEntity> findAllByUser_Id(Long userId);

}

