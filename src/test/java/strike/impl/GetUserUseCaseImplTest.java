package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.user.GetUserUseCaseImpl;
import strike.ex.persistence.domain.User;
import strike.ex.persistence.entity.UserEntity;
import strike.ex.persistence.repository.NewUserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class GetUserUseCaseImplTest {

    @Mock
    private NewUserRepository userRepository;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Test
    void testGetUser() {
        long userId = 1L;
        UserEntity userEntity = new UserEntity();
        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        Optional<User> user = getUserUseCase.getUser(userId);

        assertTrue(user.isPresent());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUser_NotFound() {
        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Optional<User> user = getUserUseCase.getUser(userId);

        assertFalse(user.isPresent());
        verify(userRepository, times(1)).findById(userId);
    }
}
