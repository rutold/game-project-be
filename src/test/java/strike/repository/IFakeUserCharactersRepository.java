package strike.repository;

import strike.ex.persistence.entity.UserCharactersEntity;

import java.util.List;
import java.util.Optional;

public interface IFakeUserCharactersRepository {
    List<UserCharactersEntity> findAll();
    Optional<UserCharactersEntity> findById(long id);
    UserCharactersEntity save(UserCharactersEntity userCharacter);
    void deleteById(long id);
}