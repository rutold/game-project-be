package strike.ex.persistence.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import strike.ex.persistence.domain.TopGamesRequest;
import strike.ex.persistence.domain.TopScoresRequest;
import strike.ex.persistence.entity.GameEntity;
import strike.ex.persistence.entity.GameScoreEntity;
import strike.ex.persistence.entity.UserEntity;

import java.util.List;
import java.util.Optional;


@Repository
public interface GameScoreRepository extends JpaRepository<GameScoreEntity, Long> {
    List<Optional<GameScoreEntity>> findByGameEntityAndUserEntity(GameEntity game, UserEntity user);
    @Query("SELECT NEW strike.ex.persistence.domain.TopScoresRequest(gs.gameEntity.id, gs.gameEntity.name, gs.gameEntity.difficulty, MAX(gs.score), gs.userEntity.username) " +
            "FROM GameScoreEntity gs " +
            "GROUP BY gs.gameEntity.id, gs.userEntity.username " +
            "ORDER BY MAX(gs.score) DESC")
    List<TopScoresRequest> getTop10ByScore(PageRequest pageRequest);
    @Query("SELECT NEW strike.ex.persistence.domain.TopGamesRequest(gs.gameEntity.id, gs.gameEntity.name, gs.gameEntity.difficulty, COUNT(gs)) " +
            "FROM GameScoreEntity gs " +
            "GROUP BY gs.gameEntity.id, gs.gameEntity.name, gs.gameEntity.difficulty " +
            "ORDER BY COUNT(gs) DESC")
    List<TopGamesRequest> findTop10MostPlayedGames(PageRequest pageRequest);

}


