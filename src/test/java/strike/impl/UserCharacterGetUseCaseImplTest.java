package strike.impl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.character.UserCharacterGetUseCaseImpl;
import strike.ex.persistence.entity.UserCharactersEntity;
import strike.ex.persistence.repository.UserCharactersRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class UserCharacterGetUseCaseImplTest {

    @Mock
    private UserCharactersRepository userCharactersRepository;

    @InjectMocks
    private UserCharacterGetUseCaseImpl userCharacterGetUseCase;

    @Test
    void testGetUserCharacter() {
        long userId = 1L;
        long characterId = 1L;
        UserCharactersEntity userCharacter = new UserCharactersEntity();

        when(userCharactersRepository.findByUser_IdAndCharacter_Id(userId, characterId)).thenReturn(Optional.of(userCharacter));

        Optional<UserCharactersEntity> result = userCharacterGetUseCase.getUserCharacter(userId, characterId);

        verify(userCharactersRepository, times(1)).findByUser_IdAndCharacter_Id(userId, characterId);
        assertTrue(result.isPresent());
    }
}
