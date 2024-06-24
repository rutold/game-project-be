package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.character.CharacterGetAllUseCaseImpl;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.repository.CharacterRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class CharacterGetAllUseCaseImplTest {

    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private CharacterGetAllUseCaseImpl characterGetAllUseCase;

    @Test
    void testGetAllCharactersWithAbilities() {
        CharacterEntity character1 = new CharacterEntity();
        CharacterEntity character2 = new CharacterEntity();
        when(characterRepository.findAllWithAbilities()).thenReturn(Arrays.asList(character1, character2));

        List<CharacterEntity> characters = characterGetAllUseCase.getAllCharactersWithAbilities();

        assertEquals(2, characters.size());
        verify(characterRepository, times(1)).findAllWithAbilities();
    }
}
