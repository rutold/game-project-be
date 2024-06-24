package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.upgrades.UpgradesGetAllUseCaseImpl;
import strike.ex.persistence.entity.UpgradesEntity;
import strike.ex.persistence.repository.UpgradesRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class UpgradesGetAllUseCaseImplTest {

    @Mock
    private UpgradesRepository upgradesRepository;

    @InjectMocks
    private UpgradesGetAllUseCaseImpl upgradesGetAllUseCase;

    @Test
    void testGetAllUpgrades() {
        List<UpgradesEntity> upgrades = Arrays.asList(new UpgradesEntity(), new UpgradesEntity());

        when(upgradesRepository.findAll()).thenReturn(upgrades);

        List<UpgradesEntity> result = upgradesGetAllUseCase.getAllUpgrades();

        verify(upgradesRepository, times(1)).findAll();
        assertEquals(upgrades.size(), result.size());
    }
}
