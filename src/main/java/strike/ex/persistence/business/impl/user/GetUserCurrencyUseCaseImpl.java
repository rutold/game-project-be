package strike.ex.persistence.business.impl.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.GetUserCurrencyUseCase;
import strike.ex.persistence.entity.UserEntity;
import strike.ex.persistence.repository.NewUserRepository;

@Service
@RequiredArgsConstructor
public class GetUserCurrencyUseCaseImpl implements GetUserCurrencyUseCase {
    private final NewUserRepository userRepository;

    @Override
    public int getUserCurrency(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return user.getUserCurrency();
    }
}
