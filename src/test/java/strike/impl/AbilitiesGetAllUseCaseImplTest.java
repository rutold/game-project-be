package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.ability.AbilitiesGetAllUseCaseImpl;
import strike.ex.persistence.entity.AbilityEntity;
import strike.ex.persistence.repository.AbilityRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
class AbilitiesGetAllUseCaseImplTest {

    @Mock
    private AbilityRepository abilityRepository;

    @InjectMocks
    private AbilitiesGetAllUseCaseImpl abilitiesGetAllUseCase;

    @Test
    void testGetAllAbilities() {
        AbilityEntity ability1 = new AbilityEntity();
        AbilityEntity ability2 = new AbilityEntity();
        when(abilityRepository.findAll()).thenReturn(Arrays.asList(ability1, ability2));

        List<AbilityEntity> abilities = abilitiesGetAllUseCase.getAllAbilities();

        assertEquals(2, abilities.size());
        verify(abilityRepository, times(1)).findAll();
    }
}
