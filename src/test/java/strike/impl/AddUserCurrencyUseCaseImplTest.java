package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.exception.InvalidUserException;
import strike.ex.persistence.business.impl.user.AddUserCurrencyUseCaseImpl;
import strike.ex.persistence.entity.UserEntity;
import strike.ex.persistence.repository.NewUserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class AddUserCurrencyUseCaseImplTest {

    @Mock
    private NewUserRepository userRepository;

    @InjectMocks
    private AddUserCurrencyUseCaseImpl addUserCurrencyUseCase;

    @Test
    void testAddUserCurrency_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(InvalidUserException.class, () -> addUserCurrencyUseCase.AddUserCurrency(1L, 10));
    }

    @Test
    void testAddUserCurrency_Success() {
        UserEntity user = new UserEntity();
        user.setUserCurrency(10);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        int result = addUserCurrencyUseCase.AddUserCurrency(1L, 20);

        assertEquals(30, result);
        verify(userRepository, times(1)).save(user);
    }
}