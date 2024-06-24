package strike.repository.impl;

import org.springframework.stereotype.Repository;
import strike.ex.persistence.entity.UserCharactersEntity;
import strike.repository.IFakeUserCharactersRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeUserCharactersRepositoryImpl implements IFakeUserCharactersRepository {
    private static long NEXT_ID = 1;
    private final List<UserCharactersEntity> userCharacters = new ArrayList<>();

    @Override
    public List<UserCharactersEntity> findAll() {
        return Collections.unmodifiableList(userCharacters);
    }

    @Override
    public Optional<UserCharactersEntity> findById(long id) {
        return userCharacters.stream().filter(userCharacter -> userCharacter.getId().equals(id)).findFirst();
    }

    @Override
    public UserCharactersEntity save(UserCharactersEntity userCharacter) {
        if (userCharacter.getId() == null) {
            userCharacter.setId(NEXT_ID++);
        }
        userCharacters.add(userCharacter);
        return userCharacter;
    }

    @Override
    public void deleteById(long id) {
        userCharacters.removeIf(userCharacter -> userCharacter.getId().equals(id));
    }
}