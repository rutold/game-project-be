package strike.ex.persistence.business.impl.user;

import strike.ex.persistence.business.GetUserUseCase;
import strike.ex.persistence.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.repository.NewUserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private final NewUserRepository userRepository;

    @Override
    public Optional<User> getUser(long userid) {
        return userRepository.findById(userid).map(UserConverter::convert);
    }
}
