package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.user.DeleteUserUseCaseImpl;
import strike.ex.persistence.repository.NewUserRepository;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class DeleteUserUseCaseImplTest {

    @Mock
    private NewUserRepository userRepository;

    @InjectMocks
    private DeleteUserUseCaseImpl deleteUserUseCase;

    @Test
    void testDeleteUser() {
        long userId = 1L;

        deleteUserUseCase.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
