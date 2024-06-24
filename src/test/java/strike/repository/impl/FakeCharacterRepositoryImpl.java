package strike.repository.impl;

import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.CharacterEntity;
import strike.repository.IFakeCharacterRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeCharacterRepositoryImpl implements IFakeCharacterRepository {
    private static long NEXT_ID = 1;
    private final List<CharacterEntity> characters = new ArrayList<>();

    @Override
    public List<CharacterEntity> findAllWithAbilities() {
        return Collections.unmodifiableList(characters);
    }

    @Override
    public Optional<CharacterEntity> findById(long id) {
        return characters.stream().filter(character -> character.getId().equals(id)).findFirst();
    }

    @Override
    public CharacterEntity findByNameWithAbilities(String name) {
        return characters.stream().filter(character -> character.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public CharacterEntity save(CharacterEntity character) {
        if (character.getId() == null) {
            character.setId(NEXT_ID++);
        }
        characters.add(character);
        return character;
    }

    @Override
    public void deleteById(long id) {
        characters.removeIf(character -> character.getId().equals(id));
    }
}
