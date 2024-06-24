package strike.repository;

import strike.ex.persistence.entity.CharacterEntity;

import java.util.List;
import java.util.Optional;

public interface IFakeCharacterRepository {
    List<CharacterEntity> findAllWithAbilities();
    Optional<CharacterEntity> findById(long id);
    CharacterEntity findByNameWithAbilities(String name);
    CharacterEntity save(CharacterEntity character);
    void deleteById(long id);
}
