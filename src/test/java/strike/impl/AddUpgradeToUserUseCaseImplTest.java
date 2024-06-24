package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.upgrades.AddUpgradeToUserUseCaseImpl;
import strike.ex.persistence.entity.UpgradesEntity;
import strike.ex.persistence.entity.UserEntity;
import strike.ex.persistence.entity.UserUpgradesEntity;
import strike.ex.persistence.repository.NewUserRepository;
import strike.ex.persistence.repository.UpgradesRepository;
import strike.ex.persistence.repository.UserUpgradesRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class AddUpgradeToUserUseCaseImplTest {

    @Mock
    private NewUserRepository userRepository;

    @Mock
    private UpgradesRepository upgradesRepository;

    @Mock
    private UserUpgradesRepository userUpgradesRepository;

    @InjectMocks
    private AddUpgradeToUserUseCaseImpl addUpgradeToUserUseCase;

    @Test
    void testAddUpgradeToUser_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> addUpgradeToUserUseCase.addUpgradeToUser(1L, 2L));
    }

    @Test
    void testAddUpgradeToUser_UpgradeNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(new UserEntity()));
        when(upgradesRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> addUpgradeToUserUseCase.addUpgradeToUser(1L, 2L));
    }

    @Test
    void testAddUpgradeToUser_UpgradeAlreadyOwned() {
        UserEntity user = new UserEntity();
        UpgradesEntity upgrade = new UpgradesEntity();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(upgradesRepository.findById(2L)).thenReturn(Optional.of(upgrade));
        when(userUpgradesRepository.findByUser_IdAndUpgrade_Id(1L, 2L)).thenReturn(Optional.of(new UserUpgradesEntity()));

        assertThrows(IllegalArgumentException.class, () -> addUpgradeToUserUseCase.addUpgradeToUser(1L, 2L));
    }

    @Test
    void testAddUpgradeToUser_InsufficientCurrency() {
        UserEntity user = new UserEntity();
        user.setUserCurrency(10);
        UpgradesEntity upgrade = new UpgradesEntity();
        upgrade.setBaseCost(20);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(upgradesRepository.findById(2L)).thenReturn(Optional.of(upgrade));
        when(userUpgradesRepository.findByUser_IdAndUpgrade_Id(1L, 2L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> addUpgradeToUserUseCase.addUpgradeToUser(1L, 2L));
    }

    @Test
    void testAddUpgradeToUser_Success() {
        UserEntity user = new UserEntity();
        user.setUserCurrency(20);
        UpgradesEntity upgrade = new UpgradesEntity();
        upgrade.setBaseCost(10);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(upgradesRepository.findById(2L)).thenReturn(Optional.of(upgrade));
        when(userUpgradesRepository.findByUser_IdAndUpgrade_Id(1L, 2L)).thenReturn(Optional.empty());

        addUpgradeToUserUseCase.addUpgradeToUser(1L, 2L);

        verify(userRepository, times(1)).save(user);
        verify(userUpgradesRepository, times(1)).save(any(UserUpgradesEntity.class));
    }
}