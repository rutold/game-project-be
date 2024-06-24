package strike.ex.persistence.business.impl.character;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import strike.ex.persistence.business.AddCharacterToUserUseCase;
import strike.ex.persistence.entity.*;
import strike.ex.persistence.repository.CharacterRepository;
import strike.ex.persistence.repository.NewUserRepository;
import strike.ex.persistence.repository.UserCharactersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddCharacterToUserUseCaseImpl implements AddCharacterToUserUseCase {
    private final NewUserRepository userRepository;
    private final CharacterRepository characterRepository;
    private final UserCharactersRepository userCharactersRepository;

    @Override
    @Transactional
    public void addCharacterToUser(Long userId, Long characterId) {
        Optional<UserEntity> userOpt = userRepository.findById(userId);
        Optional<CharacterEntity> characterOpt = characterRepository.findById(characterId);

        if (userOpt.isEmpty() || characterOpt.isEmpty()) {
            throw new IllegalArgumentException("User or Upgrade not found");
        }

        UserEntity user = userOpt.get();
        CharacterEntity character = characterOpt.get();

        Optional<UserCharactersEntity> userCharacterOpt = userCharactersRepository.findByUser_IdAndCharacter_Id(userId, characterId);

        int cost;
        if (userCharacterOpt.isPresent()) {

            throw new IllegalArgumentException("Character already owned");

        }
        else
        {
            // User does not have this character, add it
            cost = character.getCost();
            if (user.getUserCurrency() < cost) {
                throw new IllegalArgumentException("Insufficient currency");
            }

            UserCharactersEntity newUserCharacter = UserCharactersEntity.builder()
                    .user(user)
                    .character(character)
                    .build();
            user.setUserCurrency(user.getUserCurrency() - cost);
            userCharactersRepository.save(newUserCharacter);
        }

        userRepository.save(user);
    }
}
