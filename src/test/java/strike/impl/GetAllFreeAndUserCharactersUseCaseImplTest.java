package strike.impl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.UserCharactersGetAllUseCase;
import strike.ex.persistence.business.impl.character.GetAllFreeAndUserCharactersUseCaseImpl;
import strike.ex.persistence.entity.CharacterEntity;
import strike.ex.persistence.repository.CharacterRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class GetAllFreeAndUserCharactersUseCaseImplTest {

    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private UserCharactersGetAllUseCase userCharactersGetAllUseCase;

    @InjectMocks
    private GetAllFreeAndUserCharactersUseCaseImpl getAllFreeAndUserCharactersUseCase;

    @Test
    void testGetAllFreeAndUserCharacters() {
        long userId = 1L;
        List<CharacterEntity> userCharacters = Arrays.asList(new CharacterEntity(), new CharacterEntity());
        List<CharacterEntity> freeCharacters = Arrays.asList(new CharacterEntity(), new CharacterEntity());

        when(userCharactersGetAllUseCase.getAllUserCharacters(userId)).thenReturn(List.of());
        when(characterRepository.findAllByCost()).thenReturn(freeCharacters);

        List<CharacterEntity> result = getAllFreeAndUserCharactersUseCase.getAllFreeAndUserCharacters(userId);

        verify(userCharactersGetAllUseCase, times(1)).getAllUserCharacters(userId);
        verify(characterRepository, times(1)).findAllByCost();
        assert(result.size() == freeCharacters.size());
    }
}
