package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.upgrades.UserUpgradesGetAllUseCaseImpl;
import strike.ex.persistence.domain.UserUpgradeDTO;
import strike.ex.persistence.entity.UpgradesEntity;
import strike.ex.persistence.entity.UserEntity;
import strike.ex.persistence.entity.UserUpgradesEntity;
import strike.ex.persistence.repository.UserUpgradesRepository;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class UserUpgradesGetAllUseCaseImplTest {

    @Mock
    private UserUpgradesRepository userUpgradesRepository;

    @InjectMocks
    private UserUpgradesGetAllUseCaseImpl userUpgradesGetAllUseCase;

    @Test
    void testGetAllUserUpgrades() {
        long userId = 1L;

        UserEntity user = new UserEntity();
        user.setId(userId);

        UserUpgradesEntity userUpgrade1 = new UserUpgradesEntity();
        userUpgrade1.setUser(user);
        userUpgrade1.setUpgrade(new UpgradesEntity());

        UserUpgradesEntity userUpgrade2 = new UserUpgradesEntity();
        userUpgrade2.setUser(user);
        userUpgrade2.setUpgrade(new UpgradesEntity());

        List<UserUpgradesEntity> userUpgrades = Arrays.asList(userUpgrade1, userUpgrade2);

        when(userUpgradesRepository.findAllByUser_Id(userId)).thenReturn(userUpgrades);

        List<UserUpgradeDTO> result = userUpgradesGetAllUseCase.getAllUserUpgrades(userId);

        verify(userUpgradesRepository, times(1)).findAllByUser_Id(userId);
        assertEquals(userUpgrades.size(), result.size());
    }
}
