package strike.ex.persistence.business.impl.upgrades;

import org.springframework.security.crypto.password.PasswordEncoder;
import strike.ex.persistence.business.UpdateUserUseCase;
import strike.ex.persistence.domain.UpdateUserRequest;
import strike.ex.persistence.business.exception.InvalidUserException;
import strike.ex.persistence.repository.NewUserRepository;
import strike.ex.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final NewUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void updateUser(String email, UpdateUserRequest request) {
        Optional<UserEntity> userOptional = userRepository.findFirstByUsername(email);
        if (userOptional.isEmpty()) {
            throw new InvalidUserException("USER_NOT_VALID");
        }

        UserEntity user = userOptional.get();
        updateFields(request, user);
    }

    private void updateFields(UpdateUserRequest request, UserEntity user) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);
        user.setUsername(request.getUsername());
        userRepository.save(user);
    }
}
