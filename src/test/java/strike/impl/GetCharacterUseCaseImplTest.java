package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.character.GetCharacterUseCaseImpl;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.repository.CharacterRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class GetCharacterUseCaseImplTest {

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private GetCharacterUseCaseImpl getCharacterUseCase;

    @Test
    void testGetCharactersWithAbilities() {
        String name = "TestCharacter";
        CharacterEntity character = new CharacterEntity();
        when(characterRepository.findByNameWithAbilities(name)).thenReturn(character);

        CharacterEntity result = getCharacterUseCase.getCharactersWithAbilities(name);

        assertNotNull(result);
        assertEquals(character, result);
        verify(characterRepository, times(1)).findByNameWithAbilities(name);
    }
}
