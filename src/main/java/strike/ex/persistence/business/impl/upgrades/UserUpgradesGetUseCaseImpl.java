package strike.ex.persistence.business.impl.upgrades;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strike.ex.persistence.business.UserUpgradesGetUseCase;
import strike.ex.persistence.entity.UserUpgradesEntity;
import strike.ex.persistence.repository.UserUpgradesRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserUpgradesGetUseCaseImpl implements UserUpgradesGetUseCase {
    private final UserUpgradesRepository userUpgradesRepository;

    @Override
    public UserUpgradesEntity getAllUserUpgrades(long userId, long upgradeId) {

        Optional<UserUpgradesEntity> userUpgradeOpt = userUpgradesRepository.findByUser_IdAndUpgrade_Id(userId, upgradeId);
        return userUpgradeOpt.get();

    }
}



