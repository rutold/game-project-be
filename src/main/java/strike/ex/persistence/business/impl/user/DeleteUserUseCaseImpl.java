package strike.ex.persistence.business.impl.user;

import strike.ex.persistence.business.DeleteUserUseCase;
import strike.ex.persistence.repository.NewUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final NewUserRepository userRepository;

    @Override
    public void deleteUser(long userID) {
        this.userRepository.deleteById(userID);
    }
}
