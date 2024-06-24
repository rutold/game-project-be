package strike.ex.persistence.business.impl.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import strike.ex.persistence.business.CreateUserUseCase;
import strike.ex.persistence.business.exception.InvalidCredentialsException;
import strike.ex.persistence.configuration.security.token.AccessTokenEncoder;
import strike.ex.persistence.configuration.security.token.impl.AccessTokenImpl;
import strike.ex.persistence.domain.CreateUserRequest;
import strike.ex.persistence.domain.CreateUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.domain.LoginRequest;
import strike.ex.persistence.domain.LoginResponse;
import strike.ex.persistence.entity.RoleEnum;
import strike.ex.persistence.entity.UserRoleEntity;
import strike.ex.persistence.repository.NewUserRepository;
import strike.ex.persistence.entity.UserEntity;
import strike.ex.persistence.business.exception.UsernameAlreadyExistsException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final NewUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        UserEntity savedUser = saveNewUser(request);
        return CreateUserResponse.builder()
                .userID(savedUser.getId())
                .build();
    }

    private UserEntity saveNewUser(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity newUser = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encodedPassword)
                .build();

        UserRoleEntity userRole = UserRoleEntity.builder()
                .user(newUser)
                .role(RoleEnum.USER)
                .build();

        newUser.setUserRoles(Set.of(userRole));

        return userRepository.save(newUser);
    }



    private final AccessTokenEncoder accessTokenEncoder;

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        List<String> roles = user.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getUsername(), user.getId(), roles));
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<UserEntity> userOptional = userRepository.findFirstByUsername(loginRequest.getUsername());
        UserEntity user = userOptional.orElseThrow(() -> new InvalidCredentialsException());

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}

