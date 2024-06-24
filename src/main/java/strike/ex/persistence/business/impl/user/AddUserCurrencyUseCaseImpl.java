package strike.ex.persistence.business.impl.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.AddUserCurrencyUseCase;
import strike.ex.persistence.business.exception.InvalidUserException;
import strike.ex.persistence.repository.NewUserRepository;

@Service
@RequiredArgsConstructor
public class AddUserCurrencyUseCaseImpl implements AddUserCurrencyUseCase {
    private final NewUserRepository userRepository;

    @Override
    public int AddUserCurrency(Long userId, int score) {
        return userRepository.findById(userId).map(user -> {
            user.setUserCurrency(user.getUserCurrency() + score);
            user.setAllTimeCurrency(user.getAllTimeCurrency() + score);
            userRepository.save(user);
            return user.getUserCurrency();
        }).orElseThrow(() -> new InvalidUserException("User not found"));
    }
}
