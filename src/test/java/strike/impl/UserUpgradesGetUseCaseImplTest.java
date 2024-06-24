package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.upgrades.UserUpgradesGetUseCaseImpl;
import strike.ex.persistence.entity.UserUpgradesEntity;
import strike.ex.persistence.repository.UserUpgradesRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class UserUpgradesGetUseCaseImplTest {

    @Mock
    private UserUpgradesRepository userUpgradesRepository;

    @InjectMocks
    private UserUpgradesGetUseCaseImpl userUpgradesGetUseCase;

    @Test
    void testGetAllUserUpgrades() {
        long userId = 1L;
        long upgradeId = 1L;
        UserUpgradesEntity userUpgrade = new UserUpgradesEntity();

        when(userUpgradesRepository.findByUser_IdAndUpgrade_Id(userId, upgradeId)).thenReturn(Optional.of(userUpgrade));

        UserUpgradesEntity result = userUpgradesGetUseCase.getAllUserUpgrades(userId, upgradeId);

        verify(userUpgradesRepository, times(1)).findByUser_IdAndUpgrade_Id(userId, upgradeId);
        assertNotNull(result);
    }
}
