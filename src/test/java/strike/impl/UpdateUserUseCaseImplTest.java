package strike.impl;

import  org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.exception.InvalidUserException;
import strike.ex.persistence.business.impl.upgrades.UpdateUserUseCaseImpl;
import strike.ex.persistence.domain.UpdateUserRequest;
import strike.ex.persistence.entity.UserEntity;
import strike.ex.persistence.repository.NewUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class UpdateUserUseCaseImplTest {

    @Mock
    private NewUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    @Test
    void testUpdateUser_Success() {
        String email = "test@example.com";
        UpdateUserRequest request = new UpdateUserRequest("newEmail","newUsername",  "newPassword");
        UserEntity userEntity = new UserEntity();
        when(userRepository.findFirstByUsername(email)).thenReturn(Optional.of(userEntity));
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");

        updateUserUseCase.updateUser(email, request);

        verify(userRepository, times(1)).save(userEntity);
        assertEquals("newEmail", userEntity.getEmail());
        assertEquals("newUsername", userEntity.getUsername());
        assertEquals("encodedPassword", userEntity.getPassword());
    }

    @Test
    void testUpdateUser_UserNotFound() {
        String email = "test@example.com";
        UpdateUserRequest request = new UpdateUserRequest("newUsername", "newEmail", "newPassword");
        when(userRepository.findFirstByUsername(email)).thenReturn(Optional.empty());

        assertThrows(InvalidUserException.class, () -> updateUserUseCase.updateUser(email, request));
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}
