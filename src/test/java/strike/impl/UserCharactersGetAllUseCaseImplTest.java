package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.character.UserCharactersGetAllUseCaseImpl;
import strike.ex.persistence.domain.UserCharacterDTO;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.entity.UserCharactersEntity;
import strike.ex.persistence.entity.UserEntity;
import strike.ex.persistence.repository.UserCharactersRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class UserCharactersGetAllUseCaseImplTest {

    @Mock
    private UserCharactersRepository userCharactersRepository;

    @InjectMocks
    private UserCharactersGetAllUseCaseImpl userCharactersGetAllUseCase;

    @Test
    void testGetAllUserCharacters() {
        long userId = 1L;

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setAbilities(List.of());

        UserCharactersEntity userCharacter1 = new UserCharactersEntity();
        userCharacter1.setUser(userEntity);
        userCharacter1.setCharacter(characterEntity);

        UserCharactersEntity userCharacter2 = new UserCharactersEntity();
        userCharacter2.setUser(userEntity);
        userCharacter2.setCharacter(characterEntity);

        List<UserCharactersEntity> userCharacters = Arrays.asList(userCharacter1, userCharacter2);

        when(userCharactersRepository.findAllByUser_Id(userId)).thenReturn(userCharacters);

        List<UserCharacterDTO> result = userCharactersGetAllUseCase.getAllUserCharacters(userId);

        verify(userCharactersRepository, times(1)).findAllByUser_Id(userId);
        assertEquals(userCharacters.size(), result.size());
    }
}
