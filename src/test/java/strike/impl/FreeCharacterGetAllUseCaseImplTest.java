package strike.impl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.character.FreeCharacterGetAllUseCaseImpl;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.repository.CharacterRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class FreeCharacterGetAllUseCaseImplTest {

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private FreeCharacterGetAllUseCaseImpl freeCharacterGetAllUseCase;

    @Test
    void testGetAllFreeCharacters() {
        List<CharacterEntity> freeCharacters = Arrays.asList(new CharacterEntity(), new CharacterEntity());

        when(characterRepository.findAllByCost()).thenReturn(freeCharacters);

        List<CharacterEntity> result = freeCharacterGetAllUseCase.getAllFreeCharacters();

        verify(characterRepository, times(1)).findAllByCost();
        assertEquals(freeCharacters.size(), result.size());
    }
}
