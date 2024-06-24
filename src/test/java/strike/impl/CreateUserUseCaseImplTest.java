package strike.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import strike.TestConfig;
import strike.ex.persistence.GameApplication;
import strike.ex.persistence.business.impl.user.CreateUserUseCaseImpl;
import strike.ex.persistence.configuration.security.token.AccessTokenEncoder;
import strike.ex.persistence.domain.CreateUserRequest;
import strike.ex.persistence.domain.CreateUserResponse;
import strike.ex.persistence.domain.LoginRequest;
import strike.ex.persistence.entity.UserEntity;
import strike.ex.persistence.repository.NewUserRepository;
import strike.ex.persistence.business.exception.UsernameAlreadyExistsException;
import strike.ex.persistence.business.exception.InvalidCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GameApplication.class)
@ContextConfiguration(classes = TestConfig.class)
 class CreateUserUseCaseImplTest {

    @Mock
    private NewUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Test
    void testCreateUser_Success() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> {
            UserEntity userEntity = invocation.getArgument(0);
            userEntity.setId(1L);
            return userEntity;
        });

        CreateUserRequest request = new CreateUserRequest("username", "email", "password");
        CreateUserResponse response = createUserUseCase.createUser(request);

        assertNotNull(response);
        assertNotNull(response.getUserID());
    }

    @Test
    void testCreateUser_UsernameAlreadyExists() {

        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        CreateUserRequest request = new CreateUserRequest("existing_username", "email", "password");
        assertThrows(UsernameAlreadyExistsException.class, () -> createUserUseCase.createUser(request));
    }

    @Test
    void testLogin_InvalidCredentials() {
        when(userRepository.findFirstByUsername(anyString())).thenReturn(Optional.of(new UserEntity("username", "email", "password")));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        LoginRequest request = new LoginRequest("username", "wrong_password");
        assertThrows(InvalidCredentialsException.class, () -> createUserUseCase.login(request));
    }

    @Test
    void testLogin_UserNotFound() {
        when(userRepository.findFirstByUsername(anyString())).thenReturn(Optional.empty());

        LoginRequest request = new LoginRequest("nonexistent_username", "password");
        assertThrows(InvalidCredentialsException.class, () -> createUserUseCase.login(request));
    }
}